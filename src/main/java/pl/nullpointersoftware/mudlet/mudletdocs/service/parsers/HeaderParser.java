package pl.nullpointersoftware.mudlet.mudletdocs.service.parsers;

import org.springframework.stereotype.Component;
import pl.nullpointersoftware.mudlet.mudletdocs.model.LuaDescriptor;
import pl.nullpointersoftware.mudlet.mudletdocs.service.extractors.PlainExtractor;

@Component
public class HeaderParser {

    private final PlainExtractor headerExtractor = PlainExtractor.HEADER_EXTRACTOR;

    public LuaDescriptor parse(LuaDescriptor luaDescriptor, String line) {
        String extract = headerExtractor.extract(line);
        if(extract != null) {
            return new LuaDescriptor(extract);
        }
        return luaDescriptor;
    }

    public boolean canExtract(String line) {
        return headerExtractor.canExtract(line);
    }
}
