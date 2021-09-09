package pl.nullpointersoftware.mudlet.mudletdocs.service.dropbox;

import java.nio.file.Path;

public class NoopDropBoxUploader implements DropBoxUploader {

    @Override
    public void addFile(Path path, String dir) {
    }

    @Override
    public void finish() {
    }
}
