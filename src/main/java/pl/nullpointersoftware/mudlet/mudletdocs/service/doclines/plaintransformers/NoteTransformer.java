package pl.nullpointersoftware.mudlet.mudletdocs.service.doclines.plaintransformers;

import org.springframework.stereotype.Component;

@Component
public class NoteTransformer implements PlainTransformer {

    @Override
    public String transform(String line) {
        return line.replaceAll("\\{\\{note}}", "Note: ");
    }
}
