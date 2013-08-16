package com.mquesada.friendsmap.model;

import org.springframework.social.facebook.api.FacebookProfile;

import java.util.HashSet;
import java.util.Set;

public class Profile {

    private Location location;
    private FacebookProfile facebookProfile;
    private Set<Profile> friends;
    private String pictureUrl;
    private String profileUrl;

    public Profile() {
    }

    public Profile(FacebookProfile facebookProfile) {
        this.facebookProfile = facebookProfile;
    }

    public Profile(FacebookProfile facebookProfile, Location location) {
        this.facebookProfile = facebookProfile;
        this.location = location;
    }

    public FacebookProfile getFacebookProfile() {
        return facebookProfile;
    }

    public void setFacebookProfile(FacebookProfile facebookProfile) {
        this.facebookProfile = facebookProfile;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Profile> getFriends() {
        return friends;
    }

    public void setFriends(Set<Profile> friends) {
        this.friends = friends;
    }

    public void addFriend(Profile friend) {
        if (this.friends == null) {
            this.friends = new HashSet<Profile>();
        }
        this.friends.add(friend);
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile)) return false;

        Profile profile = (Profile) o;

        if (facebookProfile != null ? !facebookProfile.equals(profile.facebookProfile) : profile.facebookProfile != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return facebookProfile != null ? facebookProfile.hashCode() : 0;
    }
}
