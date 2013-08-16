package com.mquesada.friendsmap;

import com.mquesada.friendsmap.model.Profile;
import com.mquesada.friendsmap.services.FacebookService;
import org.springframework.core.env.Environment;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class HomeController {

    private static final Logger log = Logger.getLogger(HomeController.class.getName());
    private Environment environment;
    private final Facebook facebook;
    private final FacebookService facebookService;

    @Inject
    public HomeController(Facebook facebook, FacebookService facebookService, Environment environment) {
        this.facebook = facebook;
        this.facebookService = facebookService;
        this.environment = environment;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {

        model.addAttribute("user", new Profile(facebook.userOperations().getUserProfile()));
        List<Profile> friendsData = facebookService.getFriendsData();
        for (Profile profile : friendsData) {
            if (profile.getFriends() != null) {
                for (Iterator<Profile> iterator = profile.getFriends().iterator(); iterator.hasNext(); ) {
                    Profile friend = iterator.next();
                    friend.getFriends().remove(profile);
                }
            }
        }
        model.addAttribute("friends", friendsData);
        model.addAttribute("mapKey", environment.getProperty("googleMaps.clientKey"));
        return "home";
    }

}
