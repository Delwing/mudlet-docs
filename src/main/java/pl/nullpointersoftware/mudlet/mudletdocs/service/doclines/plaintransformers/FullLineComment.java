package pl.nullpointersoftware.mudlet.mudletdocs.service.doclines.plaintransformers;

import org.springframework.stereotype.Component;

@Component
public class FullLineComment implements PlainTransformer {

    private static final String NO_WIDTH_SPACE = "\u200B";

    @Override
    public String transform(String line) {
        if (line.startsWith("--")) {
            line = line.replaceAll("^\\s*--", NO_WIDTH_SPACE + "--");
        };
        return line;
    }
}
