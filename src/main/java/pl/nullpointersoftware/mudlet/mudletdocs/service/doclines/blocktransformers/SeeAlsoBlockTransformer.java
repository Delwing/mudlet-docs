package pl.nullpointersoftware.mudlet.mudletdocs.service.doclines.blocktransformers;

import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//@Component
public class SeeAlsoBlockTransformer implements BlockTransformer {

    private final Pattern functionNames = Pattern.compile("(?:\\[\\[.*?\\|(.*?)(?:\\(\\))?]](?:, )?)");

    @Override
    public List<String> transformBlocks(List<String> docs) {
        boolean blockOpened = false;
        ListIterator<String> iterator = docs.listIterator();
        Set<String> seeAlso = new LinkedHashSet<>();
        while(iterator.hasNext()) {
            String line = iterator.next();
            if (isOpenOfBlock(line)) {
                blockOpened = true;
            }

            Matcher matcher = functionNames.matcher(line);
            boolean found = false;
            if (blockOpened) {
                while (matcher.find()) {
                    seeAlso.add(matcher.group(1));
                    found = true;
                }
            }

            if (blockOpened && ((!found && !isOpenOfBlock(line) ) || !iterator.hasNext())) {
                iterator.set(seeAlso.stream().map(funcName -> "--- see: " + funcName + "()").collect(Collectors.joining("\n", "\n", "")).replaceAll("^\\s---", "").trim());
                seeAlso.clear();
                blockOpened = false;
            } else if(isOpenOfBlock(line)) {
                iterator.set("See also:");
            } else if (blockOpened) {
                iterator.remove();
            }

        }
        return docs;
    }

    private boolean isOpenOfBlock(String line) {
        return line.trim().startsWith("See also:");
    }
}
