package com.mquesada.friendsmap;

import com.mquesada.friendsmap.model.Location;
import com.mquesada.friendsmap.model.Profile;
import com.mquesada.friendsmap.services.FacebookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("api")
public class FriendsController {
    private static final Logger log = Logger.getLogger(FriendsController.class.getName());
    private final FacebookService facebookService;

    @Inject
    public FriendsController(FacebookService facebookService) {
        this.facebookService = facebookService;
    }

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    @ResponseBody
    public List<Friend> getFriends() {
        List<Profile> friendsData = facebookService.getFriendsData();
        List<Friend> mutualFriends = new ArrayList<Friend>(friendsData.size());
        for (Profile profile : friendsData) {
            Friend friend = new Friend(profile);
            if (null != profile.getFriends()) {
                for (Profile mFriend: profile.getFriends()) {
                    mFriend.getFriends().remove(profile);
                    friend.addFriendLocation(mFriend.getLocation());
                }
            }
            mutualFriends.add(friend);
        }
        return mutualFriends;
    }


    private static class Friend extends Profile {
        private List<Location> friendsLocations;

        public Friend(Profile profile) {
            this.setId(profile.getId());
            this.setName(profile.getName());
            this.setPictureUrl(profile.getPictureUrl());
            this.setProfileUrl(profile.getProfileUrl());
            this.setLocation(profile.getLocation());
            this.friendsLocations = new ArrayList<Location>();
        }

        public void addFriendLocation(Location location) {
            this.friendsLocations.add(location);
        }

        public List<Location> getFriendsLocations() {
            return friendsLocations;
        }

    }

}
