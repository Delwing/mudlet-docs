package pl.nullpointersoftware.mudlet.mudletdocs.service.doclines.plaintransformers;

import org.springframework.stereotype.Component;

@Component
public class AddHeadersPlainTransformer implements PlainTransformer {

    @Override
    public String transform(String line) {
        return line.replaceAll("^;", "## ");
    }
}
