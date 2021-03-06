package chapter10.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SearchController {

    private final SearchEngineService service;

    @Autowired
    public SearchController(SearchEngineService service) {
        this.service = service;
    }

    @RequestMapping("q/{query}")
    public SearchResults contentOpt(@PathVariable("query") String query) throws Exception {
        return service.search(query);
    }

    @RequestMapping("click/{algorithm}/{uuid}")
    public void click(@PathVariable("algorithm") String algorithm, 
            @PathVariable("uuid") String uuid) throws Exception {
        service.registerClick(algorithm, uuid);
    }
}
