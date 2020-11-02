package pl.nullpointersoftware.mudlet.mudletdocs.service.api;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.nullpointersoftware.mudlet.mudletdocs.model.WikiResponse;

@FeignClient(name = "wiki", url = "https://wiki.mudlet.org/")
public interface WikiApiClient {

    @Headers("User-Agent: Dargoth parser")
    @RequestMapping(method = RequestMethod.GET, value = "api.php?action=parse&page={page}&prop=wikitext&formatversion=2&format=json")
    WikiResponse getPage(@PathVariable("page") String page);

}
