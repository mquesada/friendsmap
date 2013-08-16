package com.mquesada.friendsmap.model;

import org.springframework.social.facebook.api.Reference;

import java.math.BigDecimal;

public class Location extends Reference {

    private String country;
    private String countryColorCode;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public Location(String id) {
        super(id);
    }

    public Location(String id, String name) {
        super(id, name);
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }


    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryColorCode() {
        return countryColorCode;
    }

    public void setCountryColorCode(String countryColorCode) {
        this.countryColorCode = countryColorCode;
    }
}
