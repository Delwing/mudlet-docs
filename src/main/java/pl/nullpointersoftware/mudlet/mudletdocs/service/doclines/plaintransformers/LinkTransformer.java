package pl.nullpointersoftware.mudlet.mudletdocs.service.doclines.plaintransformers;

import org.springframework.stereotype.Component;
import pl.nullpointersoftware.mudlet.mudletdocs.service.doclines.BlockState;

@Component
public class LinkTransformer implements PlainTransformer {

    @Override
    public String transform(String line) {
        line = line.replaceAll("\\[(\\S+) ((?:\\w+ ?)+)]","[$2](<$1>)");
        line = line.replaceAll("\\[\\[(Manual:.*?)\\|([^]]*)]]", "[$2](<https://wiki.mudlet.org/w/$1>)");
        line = line.replaceAll("\\[\\[(Manual:.*?)]]", "[$1](<https://wiki.mudlet.org/w/$1>)");
        line = line.replaceAll("\\[\\[([^|]*)\\|([^]]*)]]", "[$2](<https://wiki.mudlet.org/w/Manual:Lua_Functions$1>)");
        line = line.replaceAll("\\[\\[([^|]*)]]", "[$1](<https://wiki.mudlet.org/w/Manual:Lua_Functions$1>)");
        return line;
    }

    @Override
    public String transform(String line, BlockState blockState) {
        if(blockState.isCodeBlockOpen()) {
            return line;
        }
        return transform(line);
    }
}
