package pl.nullpointersoftware.mudlet.mudletdocs.service.dropbox;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CommitInfo;
import com.dropbox.core.v2.files.UploadSessionCursor;
import com.dropbox.core.v2.files.UploadSessionFinishArg;
import com.dropbox.core.v2.files.UploadSessionFinishBatchLaunch;
import com.dropbox.core.v2.files.WriteMode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class DropBoxUploader {

    private final DbxClientV2 client;
    private final List<UploadSessionFinishArg> entries = new ArrayList<>();

    public DropBoxUploader(String token) {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("Mudlet Docs").build();
        client = new DbxClientV2(config, token);
    }

    public void addFile(Path path) {
        try (InputStream in = new FileInputStream(path.toFile())) {
            String sessionId = client.files().uploadSessionStart(true).uploadAndFinish(in).getSessionId();
            log.info("Uploading {} to DropBox", path.toString());
            UploadSessionCursor cursor = new UploadSessionCursor(sessionId, path.toFile().length());
            CommitInfo commitInfo = new CommitInfo("/Mudlet Docs/" + path.getFileName().toString(), WriteMode.OVERWRITE, false, new Date(), false, null, false);
            UploadSessionFinishArg arg = new UploadSessionFinishArg(cursor, commitInfo);
            entries.add((arg));
        } catch (IOException | DbxException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public UploadSessionFinishBatchLaunch finish() {
        return client.files().uploadSessionFinishBatch(entries);
    }

}
