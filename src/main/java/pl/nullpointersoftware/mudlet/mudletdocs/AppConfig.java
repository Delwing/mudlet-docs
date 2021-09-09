package pl.nullpointersoftware.mudlet.mudletdocs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "mudlet-docs")
@Data
public class AppConfig {

    private List<String> pages;

}
