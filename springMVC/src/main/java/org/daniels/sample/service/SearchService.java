package org.daniels.sample.service;

import org.daniels.sample.org.daniels.sample.search.LightTweet;
import org.daniels.sample.org.daniels.sample.search.TwitterSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by daniels on 10.07.2017.
 */
@Service
public class SearchService implements TwitterSearch{
    private Twitter twitter;

    @Autowired
    public SearchService(Twitter twitter){
        this.twitter = twitter;
    }

    public List<LightTweet>  search(String searchType, List<String> keywords) {
        List<SearchParameters> searches = keywords.stream()
                .map(taste -> createSearchParam(searchType, taste))
                .collect(Collectors.toList());

        List<LightTweet> results = searches.stream()
                .map(params -> twitter.searchOperations().search(params))
                .flatMap(searchResults -> searchResults.getTweets().stream())
                .map(LightTweet::ofTweet)
                .collect(Collectors.toList());
        return results;
    }

    private SearchParameters.ResultType getResultType(String searchType) {
        for (SearchParameters.ResultType knowType : SearchParameters.ResultType.values()) {
            if (knowType.name().equalsIgnoreCase(searchType)) {
                return knowType;
            }
        }
        return  SearchParameters.ResultType.RECENT;
    }

    private SearchParameters createSearchParam(String searchType, String taste) {
        SearchParameters.ResultType resultType = getResultType(searchType);
        SearchParameters searchParameters = new SearchParameters(taste);
        searchParameters.resultType(resultType);
        searchParameters.count(3);
        return searchParameters;
    }
}
