package pl.nullpointersoftware.mudlet.mudletdocs.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.nullpointersoftware.mudlet.mudletdocs.model.WikiResponse;

@Component
@RequiredArgsConstructor
public class WikiRestClient {

    public static final String URL = "https://wiki.mudlet.org/api.php?action=parse&page=%s&prop=wikitext&formatversion=2&format=json";
    private final RestTemplate restTemplate;

    public WikiResponse getPage(String page) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("User-Agent", "Dargoth parser");

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        return restTemplate.exchange(String.format(URL, page), HttpMethod.GET, entity, WikiResponse.class).getBody();
    }



}
