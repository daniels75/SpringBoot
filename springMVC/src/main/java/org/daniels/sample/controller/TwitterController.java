package org.daniels.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by daniels on 04.04.2017.
 */
@Controller
public class TwitterController {

    @Autowired
    private Twitter twitter;

    @RequestMapping("/")
    public String hello(Model model, @RequestParam(value = "search", defaultValue = "twitter default") String search) {
        SearchResults searchResults = twitter.searchOperations().search(search);
        String text = searchResults.getTweets().get(0).getText();

        model.addAttribute("message", text);
        return "resultPage";
    }

    @RequestMapping("/info")
    public String info(Model model, @RequestParam(value = "name", defaultValue = "word")  String userName) {
        model.addAttribute("message", "Witaj " + userName +  "!");
        return "resultPage";
    }
}
