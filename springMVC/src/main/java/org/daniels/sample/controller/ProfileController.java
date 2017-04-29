package org.daniels.sample.controller;

import org.daniels.sample.dto.ProfileForm;
import org.daniels.sample.formatters.USLocalDateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by daniels on 26.04.2017.
 */
@Controller
public class ProfileController {


    @RequestMapping("/profile")
    public String displayProfile(ProfileForm profileForm) {
        return "profile/profilePage";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String saveProfile(@Valid ProfileForm profileForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "profile/profilePage";
        }
        System.out.println("saved profile: " + profileForm);
        return "redirect:/profile";
    }

    @ModelAttribute("dateFormat")
    public String localFormat(Locale locale) {
        return USLocalDateFormatter.getPattern(locale);
    }
}
