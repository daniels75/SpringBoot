package org.daniels.sample.search.api;

import org.daniels.sample.org.daniels.sample.search.LightTweet;
import org.daniels.sample.org.daniels.sample.search.TwitterSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
// see  SearchController - it is similar
public class SearchApiController {

    private TwitterSearch twitterSearch;

    @Autowired
    public SearchApiController(TwitterSearch twitterSearch){
        this.twitterSearch = twitterSearch;
    }
    @RequestMapping(value = "/{searchType}", method = RequestMethod.GET)
    public List<LightTweet> search(@PathVariable String searchType, @MatrixVariable List<String> keywords) {
        return twitterSearch.search(searchType, keywords);
    }
}
