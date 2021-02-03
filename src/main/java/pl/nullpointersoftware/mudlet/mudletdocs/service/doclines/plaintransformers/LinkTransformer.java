package pl.nullpointersoftware.mudlet.mudletdocs.service.doclines.plaintransformers;

import org.springframework.stereotype.Component;

@Component
public class LinkTransformer implements PlainTransformer {

    @Override
    public String transform(String line) {
        line = line.replaceAll("\\[(\\S+) ((?:\\w+ ?)+)]","[$1]($2)");
        line = line.replaceAll("\\[\\[(.*)\\|(.*)]]", "[https://wiki.mudlet.org/w/$1]($2)");
        return line;
    }
}
