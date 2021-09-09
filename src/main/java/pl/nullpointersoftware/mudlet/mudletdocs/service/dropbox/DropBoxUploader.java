package pl.nullpointersoftware.mudlet.mudletdocs.service.dropbox;

import lombok.SneakyThrows;

import java.nio.file.Path;

public interface DropBoxUploader {
    default void addFile(Path path) {
        addFile(path, "");
    }
    void addFile(Path path, String dir);

    @SneakyThrows
    void finish();
}
