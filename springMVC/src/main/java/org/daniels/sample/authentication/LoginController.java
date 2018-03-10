package org.daniels.sample.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by daniels on 10.03.2018.
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String authenticate(){
        return "login";
    }
}
