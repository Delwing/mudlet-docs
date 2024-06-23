package pl.nullpointersoftware.mudlet.mudletdocs.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.nullpointersoftware.mudlet.mudletdocs.model.GithubBlobResponse;
import pl.nullpointersoftware.mudlet.mudletdocs.model.GithubTreeResponse;

@Component
@RequiredArgsConstructor
public class GithubRestClient {

    private static final String TREE_URL = "https://api.github.com/repos/Mudlet/Mudlet/git/trees/development?recursive=1";
    private static final String BLOB_URL = "https://api.github.com/repos/Mudlet/Mudlet/git/blobs/";
    private static final String TOKEN_ENV_KEY = "GITHUB_TOKEN";
    private static final String AGENT_NAME = "Dargoth parser";
    private final RestTemplate restTemplate;

    public GithubTreeResponse getTree() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.USER_AGENT, AGENT_NAME);
        String token = System.getenv(TOKEN_ENV_KEY);
        if (token != null) {
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        }

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        return restTemplate.exchange(TREE_URL, HttpMethod.GET, entity, GithubTreeResponse.class).getBody();
    }

    public GithubBlobResponse getBlob(String sha) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.AUTHORIZATION, AGENT_NAME);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        return restTemplate.exchange(BLOB_URL + sha, HttpMethod.GET, entity, GithubBlobResponse.class).getBody();
    }



}
