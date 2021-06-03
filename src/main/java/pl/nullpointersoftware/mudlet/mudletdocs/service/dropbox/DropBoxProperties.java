package pl.nullpointersoftware.mudlet.mudletdocs.service.dropbox;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "dropbox")
@Data
public class DropBoxProperties {

    private String accessToken;

}