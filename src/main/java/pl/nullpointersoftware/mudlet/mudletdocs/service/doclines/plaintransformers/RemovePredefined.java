package pl.nullpointersoftware.mudlet.mudletdocs.service.doclines.plaintransformers;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RemovePredefined implements PlainTransformer {

    private final Set<String> definitions = Set.of(
        "<span id=\"(?:get|set)discord.*?\"></span>"
    );

    @Override
    public String transform(String line) {
        for (String definition : definitions) {
            line = line.replaceAll(definition, "");
        }
        return line;
    }
}
