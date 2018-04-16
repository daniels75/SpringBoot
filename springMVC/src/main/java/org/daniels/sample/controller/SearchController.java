package org.daniels.sample.controller;

import org.daniels.sample.org.daniels.sample.search.LightTweet;
import org.daniels.sample.org.daniels.sample.search.TwitterSearch;
import org.daniels.sample.profile.UserProfileSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by daniels on 10.07.2017.
 */
@Controller
public class SearchController {
    private TwitterSearch twitterSearch;

    @Autowired
    private UserProfileSession session;

    @Autowired
    public SearchController(TwitterSearch  twitterSearch){
        this.twitterSearch = twitterSearch;
    }

    @RequestMapping("/search/{searchType}")
    public ModelAndView search(@PathVariable String searchType, @MatrixVariable List<String> keywords) {
        List<LightTweet> tweets = twitterSearch.search(searchType, keywords);
        ModelAndView modelAndView = new ModelAndView("resultPage");
        modelAndView.addObject("tweets", tweets);
        modelAndView.addObject("search", String.join(",", keywords));
        return modelAndView;
    }



}
