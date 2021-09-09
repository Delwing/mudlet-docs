package pl.nullpointersoftware.mudlet.mudletdocs;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.nullpointersoftware.mudlet.mudletdocs.model.LuaDescriptor;
import pl.nullpointersoftware.mudlet.mudletdocs.service.LuaWriter;
import pl.nullpointersoftware.mudlet.mudletdocs.service.PageAnalyzer;
import pl.nullpointersoftware.mudlet.mudletdocs.service.api.GithubRestClient;
import pl.nullpointersoftware.mudlet.mudletdocs.service.api.WikiRestClient;
import pl.nullpointersoftware.mudlet.mudletdocs.service.dropbox.DropBoxUploader;
import pl.nullpointersoftware.mudlet.mudletdocs.service.dropbox.DropBoxUploaderFactory;
import pl.nullpointersoftware.mudlet.mudletdocs.service.github.GithubDownloader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@Data
@Slf4j
public class PagesRunner implements ApplicationRunner {

    private final AppConfig appConfig;
    private final PageAnalyzer pageAnalyzer;
    private final LuaWriter luaWriter;
    private final WikiRestClient wikiRestClient;
    private final GithubDownloader githubDownloader;
    private final DropBoxUploaderFactory dropBoxUploaderFactory;

    @Override
    public void run(ApplicationArguments args) throws InterruptedException, URISyntaxException, IOException {
        DropBoxUploader dropBoxUploader = dropBoxUploaderFactory.getClient();
        ExecutorService executor = Executors.newFixedThreadPool(appConfig.getPages().size());
        appConfig.getPages().forEach(pageName -> {
            executor.submit(() -> {
                log.info("Processing {}", pageName);
                Set<LuaDescriptor> descriptors = pageAnalyzer.analyze(wikiRestClient.getPage(pageName).getParse().getWikitext());
                dropBoxUploader.addFile(luaWriter.generateFile(sanitizeFilename(pageName), descriptors));
            });
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        githubDownloader.downloadAll(dropBoxUploader);

        dropBoxUploader.addFile(luaWriter.generateFile("lfs.lua", LfsDoc.DESCRIPTORS));
        dropBoxUploader.addFile(luaWriter.generateFile("rex.lua", RexDoc.DESCRIPTORS));

        String dir = System.getProperty("user.dir") + "/Mudlet Docs/globals.lua";
        Path globalsPath = Path.of(dir);
        Files.deleteIfExists(globalsPath);
        Files.copy(Path.of(getClass().getClassLoader().getResource("globals.lua").toURI()), Path.of(dir));
        dropBoxUploader.addFile(globalsPath);

        dropBoxUploader.finish();
        log.info("We are done!");
    }

    private String sanitizeFilename(String name) {
        return name.replaceAll("Manual:", "").replaceAll("/", "_").toLowerCase() + ".lua";
    }

}
