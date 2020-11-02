package pl.nullpointersoftware.mudlet.mudletdocs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "")
@Data
public class PagesUrls {

    private List<String> pages;

}
