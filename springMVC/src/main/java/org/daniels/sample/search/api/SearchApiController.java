package org.daniels.sample.search.api;

import org.daniels.sample.org.daniels.sample.search.LightTweet;
import org.daniels.sample.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
// see  SearchController - it is similar
public class SearchApiController {

    private SearchService searchService;

    @Autowired
    public SearchApiController(SearchService searchService){
        this.searchService = searchService;
    }
    @RequestMapping(value = "/{searchType}", method = RequestMethod.GET)
    public List<LightTweet> search(@PathVariable String searchType, @MatrixVariable List<String> keywords) {
        return searchService.search(searchType, keywords);
    }
}
