package pl.nullpointersoftware.mudlet.mudletdocs;

import pl.nullpointersoftware.mudlet.mudletdocs.model.LuaDescriptor;

import java.util.Set;

public class RexDoc {

    public static Set<LuaDescriptor> DESCRIPTORS = Set.of(
        new LuaDescriptor("rex.match", "rex.match (subj, patt, [init], [cf], [ef], [lo])"),
        new LuaDescriptor("rex.find", "rex.find (subj, patt, [init], [cf], [ef], [lo])"),
        new LuaDescriptor("rex.gmatch", "rex.gmatch (subj, patt, [cf], [ef], [lo])"),
        new LuaDescriptor("rex.gsub", "rex.gsub (subj, patt, repl, [n], [cf], [ef], [lo])"),
        new LuaDescriptor("rex.split", "rex.split (subj, sep, [cf], [ef], [lo])"),
        new LuaDescriptor("rex.new", "rex.new (patt, [cf], [lo])"),
        new LuaDescriptor("rex.flags", "rex.flags ([tb])")
    );

    

    
}
