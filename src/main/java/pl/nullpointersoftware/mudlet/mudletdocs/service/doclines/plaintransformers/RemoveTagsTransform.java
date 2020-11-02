package pl.nullpointersoftware.mudlet.mudletdocs.service.doclines.plaintransformers;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RemoveTagsTransform implements PlainTransformer {

    private final Set<String> tagsToRemove = Set.of(
            "translate",
            "div"
    );

    @Override
    public String transform(String line) {
        for (String tag : tagsToRemove) {
            line = line.replaceAll(String.format("</?%s.*?>", tag), "");
        }
        return line;
    }
}
