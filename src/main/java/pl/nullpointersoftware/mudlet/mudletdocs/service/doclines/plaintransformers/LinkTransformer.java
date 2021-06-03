package pl.nullpointersoftware.mudlet.mudletdocs.service.doclines.plaintransformers;

import org.springframework.stereotype.Component;

@Component
public class LinkTransformer implements PlainTransformer {

    @Override
    public String transform(String line) {
        line = line.replaceAll("\\[(\\S+) ((?:\\w+ ?)+)]","[$2]($1)");
        line = line.replaceAll("\\[\\[([^|]*)\\|([^]]*)]]", "[$2](https://wiki.mudlet.org/w/$1)");
        line = line.replaceAll("\\[\\[([^|]*)]]", "[$1](https://wiki.mudlet.org/w/$1)");
        return line;
    }
}
