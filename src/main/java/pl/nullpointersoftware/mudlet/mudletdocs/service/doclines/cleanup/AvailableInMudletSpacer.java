package pl.nullpointersoftware.mudlet.mudletdocs.service.doclines.cleanup;

import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class AvailableInMudletSpacer implements Cleanup {

    @Override
    public List<String> cleanup(List<String> docLines) {
        if (docLines.isEmpty()) {
            return docLines;
        }
        for (int i = 0; i < docLines.size(); i++) {
            String line = docLines.get(i);
            if (line.startsWith("Available in Mudlet since")) {
                if (i == 0 || !docLines.get(i - 1).isEmpty()) {
                    docLines.add(i, "");
                    i++;
                }
                if (i != docLines.size() - 1 && !docLines.get(i + 1).isEmpty()) {
                    docLines.add(i + 1, "");
                }
                break;
            }

        }
        return docLines;
    }
}
