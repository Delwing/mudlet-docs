package pl.nullpointersoftware.mudlet.mudletdocs.service.github;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.nullpointersoftware.mudlet.mudletdocs.service.api.GithubRestClient;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

@Component
@RequiredArgsConstructor
@Slf4j
public class GithubDownloader {

    private final GithubRestClient githubRestClient;
    private final String dir = System.getProperty("user.dir") + "/MudletDocs/geyser/";

    @SneakyThrows
    public void downloadAll() {
        Files.createDirectories(Path.of(dir));
        githubRestClient.getTree().getTree().stream()
                .filter(treeItem -> treeItem.getPath().startsWith("src/mudlet-lua/lua/geyser/"))
                .parallel()
                .forEach(treeItem -> storeFile(Path.of(treeItem.getPath()).toFile().getName(), treeItem.getSha()));
    }

    @SneakyThrows
    private void storeFile(String name, String sha) {
        log.info("Downloading file {}", name);
        String content = githubRestClient.getBlob(sha).getContent();
        Path filePath = Path.of(dir + name);
        Files.write(filePath, Base64.getMimeDecoder().decode(content));
    }

}
