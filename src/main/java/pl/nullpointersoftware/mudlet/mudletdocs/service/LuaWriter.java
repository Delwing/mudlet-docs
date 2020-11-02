package pl.nullpointersoftware.mudlet.mudletdocs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.nullpointersoftware.mudlet.mudletdocs.model.LuaDescriptor;
import pl.nullpointersoftware.mudlet.mudletdocs.service.doclines.blocktransformers.BlockTransformer;
import pl.nullpointersoftware.mudlet.mudletdocs.service.doclines.cleanup.Cleanup;
import pl.nullpointersoftware.mudlet.mudletdocs.service.doclines.plaintransformers.PlainTransformer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class LuaWriter {

    private final Set<PlainTransformer> plainTransformers;
    private final Set<BlockTransformer> blockTransformers;
    private final Set<Cleanup> cleanups;

    public void generateFile(String name, Set<LuaDescriptor> descriptors) {
        String dir = System.getProperty("user.dir") + "/result/";
        try {
            Files.createDirectories(Path.of(dir));
        } catch (IOException e) {
            log.error("Cannot create directories.", e);
        }
        Path filePath = Path.of(dir + name);
        try {
            Files.writeString(filePath, "", StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            log.error("Cannot prepare file {} for writing.", filePath, e);
        }
        descriptors.stream().filter(Objects::nonNull).map(this::functionGenerate).forEach(content -> {
            try {
                Files.writeString(filePath, content, StandardOpenOption.APPEND);
            } catch (IOException e) {
                log.error("Cannot write to file {}.", filePath, e);
            }
        });
    }

    private String functionGenerate(LuaDescriptor descriptor) {
        ListIterator<String> docsIterator = descriptor.getDocLines().listIterator();
        while (docsIterator.hasNext()) {
            docsIterator.set(transform(docsIterator.next()));
        }

        for (BlockTransformer blockTransformer : blockTransformers) {
            descriptor.setDocLines(blockTransformer.transformBlocks(descriptor.getDocLines()));
        }

        for (Cleanup cleanup : cleanups) {
            descriptor.setDocLines(cleanup.cleanup(descriptor.getDocLines()));
        }

        StringBuilder sb = new StringBuilder();
        descriptor.getDocLines().stream().map(line -> "--- " + line).forEach(line -> sb.append(line).append("\n"));
        sb.append("function ");
        sb.append(sanitizerFunction(descriptor));
        sb.append("\n");
        sb.append("end");
        sb.append("\n\n");
        return sb.toString();
    }

    public String transform(String line) {
        for (PlainTransformer plainTransformer : plainTransformers) {
            line = plainTransformer.transform(line);
        }
        return line;
    }

    public static String sanitizerFunction(LuaDescriptor descriptor) {
        String func = descriptor.getFunctionSignature();

        if (Objects.isNull(func)) {
            func = descriptor.getFunctionName() + "()";
        }

        func = func.replaceAll("</?nowiki>", "");
        func = func.replaceAll("^.* = (.*\\()", "$1");

        func = func.replaceAll("\\[,", ", [");

        func = func.replaceAll("(\\w+)\\s+\\(", "$1(");

        Pattern pattern = Pattern.compile("\\[.*\\]");
        Matcher matcher = pattern.matcher(func);
        while(matcher.find()) {
            String param = matcher.group(0);
            func = func.replace(param, param.replaceAll(" = .*\\]", "").replaceAll("(?:\\[, (\\S*).*?\\])", ", $1").replaceAll("\\[|\\]", ""));
        }

        func = func.trim().replaceAll("(?<!,) ", "_");

        if(!func.endsWith(")")) {
            func += "()";
        }

        return func;
    }

}
