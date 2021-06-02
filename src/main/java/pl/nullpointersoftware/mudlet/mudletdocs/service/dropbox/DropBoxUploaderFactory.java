package pl.nullpointersoftware.mudlet.mudletdocs.service.dropbox;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DropBoxUploaderFactory {

    private final DropBoxProperties dropBoxProperties;

    public DropBoxUploader getClient() {
        return new DropBoxUploader(dropBoxProperties.getAccessToken());
    }

}
