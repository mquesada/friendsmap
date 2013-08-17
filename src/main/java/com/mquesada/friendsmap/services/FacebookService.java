package com.mquesada.friendsmap.services;

import com.mquesada.friendsmap.model.Location;
import com.mquesada.friendsmap.model.Profile;
import com.mquesada.friendsmap.util.CountryColorHelper;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FqlResult;
import org.springframework.social.facebook.api.FqlResultMapper;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Service class that provides methods to access Facebook services.
 */
public class FacebookService {

    private static final Logger log = Logger.getLogger(FacebookService.class.getName());
    private final Facebook facebook;

    @Inject
    public FacebookService(Facebook facebook) {
        this.facebook = facebook;
    }

    /**
     * Builds a query string to get the mutual friends between the current user and a group of his/her friends.
     * @param friends The list of the user's friends.
     * @param start The index of the first friend
     * @param end The index of the last friend
     * @return A String object with the query
     */
    private String getMutualFriendsQuery(List<Profile> friends, int start, int end) {
        StringBuilder query = new StringBuilder("SELECT uid1, uid2 FROM friend WHERE uid1 IN (SELECT uid2 FROM friend WHERE uid1=me()) AND uid2 IN (");
        for (int i = start; i < end; i++) {
            query.append("'").append(friends.get(i).getId()).append("'");
            if (i < end - 1) {
                query.append(", ");
            }

        }
        query.append(")");
        return query.toString();
    }

    /**
     * Gets the information of the current user's friends.
     * @return A list of Profile objects that represent the current user's friends.
     */
    public List<Profile> getFriendsData() {
        List<Profile> results = facebook.fqlOperations().query("SELECT uid, username, name, first_name, last_name, sex, locale, current_location, hometown_location, pic_square, profile_url FROM user WHERE uid in (SELECT uid2 FROM friend WHERE uid1 = me())", new FqlResultMapper<Profile>() {
            @Override
            public Profile mapObject(FqlResult result) {
                Profile profile = new Profile();
                profile.setId(result.getString("uid"));
                profile.setName(result.getString("name"));
                profile.setFirstName(result.getString("first_name"));
                profile.setLastName(result.getString("last_name"));
                profile.setPictureUrl(result.getString("pic_square"));
                profile.setProfileUrl(result.getString("profile_url"));
                profile.setLocation(getLocation(result, "current_location"));
                if (profile.getLocation() == null) {
                    profile.setLocation(getLocation(result, "hometown_location"));
                }
                return profile;
            }
        });

        // Creates a map with the list of friends so they can be easily found by id.
        Map<String, Profile> friendProfiles = new HashMap<String, Profile>(results.size(), 1);
        for (Profile profile : results) {
            if (profile.getLocation() != null) {
                friendProfiles.put(profile.getId(), profile);
            }
        }

        // Sets the mutual friends between the current user and his/her friends.
        List<Profile> friends = new ArrayList<Profile>(friendProfiles.values());
        final int BATCH_SIZE = 50;
        int start = 0;
        int end = start + BATCH_SIZE;
        if (friends.size() <= BATCH_SIZE) {
            end = friends.size();
        }
        while (start < friends.size()) {
            String query = getMutualFriendsQuery(friends, start, end);
            List<Connection> connections = facebook.fqlOperations().query(query, new FqlResultMapper<Connection>() {
                @Override
                public Connection mapObject(FqlResult fqlResult) {
                    return new Connection(fqlResult.getString("uid1"), fqlResult.getString("uid2"));
                }
            });

            if (connections != null && connections.size() > 0) {
                for (Connection connection : connections) {
                    Profile m1 = friendProfiles.get(connection.member1);
                    Profile m2 = friendProfiles.get(connection.member2);
                    if (m1 != null && m2 != null) {
                        m1.addFriend(m2);
                    }
                }
            }
            start = end;
            end = start + BATCH_SIZE;
            if (end >= friends.size()) {
                end = friends.size();
            }
        }
        return friends;
    }

    /**
     * Gets the location information from a FQL result object.
     * @param result The FQL result object.
     * @param locationName The location name which can be 'current_location' or 'hometown_location'
     * @return Returns a Location object
     */
    public Location getLocation(FqlResult result, String locationName) {
        return result.getObject(locationName, new FqlResultMapper<Location>() {
            @Override
            public Location mapObject(FqlResult locationResult) {
                Location location = new Location(locationResult.getString("id"), locationResult.getString("name"));
                location.setLatitude(new BigDecimal(locationResult.getFloat("latitude")));
                location.setLongitude(new BigDecimal(locationResult.getFloat("longitude")));
                location.setCountry(locationResult.getString("country"));
                location.setCountryColorCode(CountryColorHelper.INSTANCE.getCountryColorCode(location.getCountry()));

                return location;
            }
        });
    }

    /**
     * Simple class to represent a connection between one user and another.
     */
    private static class Connection {
        private String member1;
        private String member2;

        private Connection(String member1, String member2) {
            this.member1 = member1;
            this.member2 = member2;
        }
    }

}
