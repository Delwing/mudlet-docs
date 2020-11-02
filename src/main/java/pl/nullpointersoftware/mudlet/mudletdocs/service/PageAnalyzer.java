package pl.nullpointersoftware.mudlet.mudletdocs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.nullpointersoftware.mudlet.mudletdocs.model.LuaDescriptor;
import pl.nullpointersoftware.mudlet.mudletdocs.service.parsers.FunctionNameParser;
import pl.nullpointersoftware.mudlet.mudletdocs.service.parsers.HeaderParser;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class PageAnalyzer {

    private final HeaderParser headerParser;
    private final FunctionNameParser functionNameParser;

    public Set<LuaDescriptor> analyze(String wikiText) {
        String[] split = wikiText.split("\n");
        Set<LuaDescriptor> luaDescriptors = new LinkedHashSet<>();

        LuaDescriptor cDescriptor = null;

        for (String line : split) {
            cDescriptor = parseLine(cDescriptor, line);
            luaDescriptors.add(cDescriptor);
        }

        return luaDescriptors;
    }

    private LuaDescriptor parseLine(LuaDescriptor descriptor, String line) {
        if (headerParser.canExtract(line)) {
            descriptor = headerParser.parse(descriptor, line);
            return descriptor;
        }

        if (descriptor == null) {
            return null;
        }

        if (functionNameParser.canExtract(line) && Objects.isNull(descriptor.getFunctionSignature())) {
            descriptor = functionNameParser.parse(descriptor, line);
            return descriptor;
        }

        descriptor.getDocLines().add(line);

        return descriptor;
    }

}