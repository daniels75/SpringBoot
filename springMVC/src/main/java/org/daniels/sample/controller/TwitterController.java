package org.daniels.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by daniels on 04.04.2017.
 */
@Controller
public class TwitterController {

    @Autowired
    private Twitter twitter;

    @RequestMapping("/testme")
    public String home() {
        return "searchPage";
    }

    @RequestMapping(value = "/postSearch", method = RequestMethod.POST)
    public String postSearch(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String search = request.getParameter("search");
        if(search.toLowerCase().contains("test")) {
            redirectAttributes.addFlashAttribute("error", "Try to find by SpringMVC");
            return  "redirect:/";
        }
        redirectAttributes.addAttribute("search", search);
        return "redirect:result";
    }

    @RequestMapping("/result")
    public String result(@RequestParam(value = "search", defaultValue = "SpringFramework") String search, Model model) {
        SearchResults searchResults = twitter.searchOperations().search(search);

        List<Tweet> tweets = searchResults.getTweets();

        model.addAttribute("tweets", tweets);
        model.addAttribute("search", search);
        return "resultPage";
    }



    @RequestMapping("/info")
    public String info(Model model, @RequestParam(value = "name", defaultValue = "word")  String userName) {
        model.addAttribute("message", "Hello " + userName +  "!");
        return "info";
    }
}
