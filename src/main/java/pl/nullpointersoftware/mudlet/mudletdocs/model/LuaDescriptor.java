package pl.nullpointersoftware.mudlet.mudletdocs.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Data
@RequiredArgsConstructor
public class LuaDescriptor {

    private String functionName;
    private String functionSignature;
    private Map<String, String> params;
    private List<String> docLines = new ArrayList<>();
    private Set<String> seeAlso = new HashSet<>();

    public LuaDescriptor(String functionName) {
        this.functionName = functionName;
    }

    public LuaDescriptor(String functionName, String functionSignature) {
        this(functionName);
        this.functionSignature = functionSignature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LuaDescriptor that = (LuaDescriptor) o;

        return functionName.equals(that.functionName);
    }

    @Override
    public int hashCode() {
        return functionName.hashCode();
    }
}