package pl.nullpointersoftware.mudlet.mudletdocs.service.doclines.plaintransformers;

import pl.nullpointersoftware.mudlet.mudletdocs.service.doclines.BlockState;

public interface PlainTransformer {

    String transform(String line);

    default String transform(String line, BlockState blockState) {
        return transform(line);
    }

}
