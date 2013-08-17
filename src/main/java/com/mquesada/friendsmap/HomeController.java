package com.mquesada.friendsmap;

import org.springframework.core.env.Environment;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.logging.Logger;

@Controller
public class HomeController {

    private static final Logger log = Logger.getLogger(HomeController.class.getName());
    private Environment environment;
    private final Facebook facebook;

    @Inject
    public HomeController(Facebook facebook, Environment environment) {
        this.facebook = facebook;
        this.environment = environment;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("user", facebook.userOperations().getUserProfile());
        model.addAttribute("mapKey", environment.getProperty("googleMaps.clientKey"));
        return "home";
    }

}
