package pl.nullpointersoftware.mudlet.mudletdocs;

import pl.nullpointersoftware.mudlet.mudletdocs.model.LuaDescriptor;

import java.util.Set;

public class LfsDoc {

    public static Set<LuaDescriptor> DESCRIPTORS = Set.of(
            new LuaDescriptor("lfs.attributes", "lfs.attributes(filepath, request_name, result_table)"),
            new LuaDescriptor("lfs.chdir", "lfs.chdir(path)"),
            new LuaDescriptor("lfs.dir", "lfs.dir(path)"),
            new LuaDescriptor("lfs.lock_dir", "lfs.lock_dir(path, seconds_stale])"),
            new LuaDescriptor("lfs.currentdir", "lfs.currentdir()"),
            new LuaDescriptor("lfs.lock", "lfs.lock(filehandle, mode, start, length)"),
            new LuaDescriptor("lfs.link", "lfs.link(old, new_, symlink)"),
            new LuaDescriptor("lfs.mkdir", "lfs.mkdir(dirname)"),
            new LuaDescriptor("lfs.rmdir", "lfs.rmdir(dirname)"),
            new LuaDescriptor("lfs.setmode", "lfs.setmode(file, mode)"),
            new LuaDescriptor("lfs.symlinkattributes", "lfs.symlinkattributes(filepath, request_name])"),
            new LuaDescriptor("lfs.touch", "lfs.touch(filepath, atime, mtime)"),
            new LuaDescriptor("lfs.unlock", "lfs.unlock(filehandle, start, length)")
    );

    

    
}
