package pl.nullpointersoftware.mudlet.mudletdocs.service.dropbox;

import lombok.SneakyThrows;

import java.nio.file.Path;

public interface DropBoxUploader {
    void addFile(Path path);

    @SneakyThrows
    void finish();
}
