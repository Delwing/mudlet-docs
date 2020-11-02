package pl.nullpointersoftware.mudlet.mudletdocs.service.doclines.cleanup;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveTrailingBlankLines implements Cleanup {

    @Override
    public List<String> cleanup(List<String> docLines) {
        if (docLines.isEmpty()) {
            return docLines;
        }
        while (docLines.get(docLines.size() - 1).isBlank()) {
            docLines.remove(docLines.size() - 1);
        }
        return docLines;
    }
}
