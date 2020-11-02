package pl.nullpointersoftware.mudlet.mudletdocs.service.parsers;

import org.springframework.stereotype.Component;
import pl.nullpointersoftware.mudlet.mudletdocs.model.LuaDescriptor;
import pl.nullpointersoftware.mudlet.mudletdocs.service.extractors.PlainExtractor;

import java.util.Objects;

@Component
public class FunctionNameParser {

    private final PlainExtractor functionSignature = PlainExtractor.FUNCTION_SIGNATURE;

    public LuaDescriptor parse(LuaDescriptor luaDescriptor, String line) {
        String extract = functionSignature.extract(line);
        if (Objects.nonNull(extract)) {
            luaDescriptor.setFunctionSignature(extract.trim());
        }
        return luaDescriptor;
    }

    public boolean canExtract(String line) {
        return functionSignature.canExtract(line);
    }
}
