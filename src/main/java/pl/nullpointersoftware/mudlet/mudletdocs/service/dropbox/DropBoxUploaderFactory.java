package pl.nullpointersoftware.mudlet.mudletdocs.service.dropbox;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DropBoxUploaderFactory {

    private final DropBoxProperties dropBoxProperties;

    public DropBoxUploader getClient() {
        return Objects.nonNull(dropBoxProperties.getAccessToken()) ? new DropBoxUploaderImpl(dropBoxProperties.getAccessToken()) : new NoopDropBoxUploader();
    }

}
