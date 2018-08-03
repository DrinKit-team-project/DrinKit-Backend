package com.teamproject.drinkit.security.social;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfoFromFacebook implements UserInfoFromSocial {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String username;

    @JsonProperty(value = "picture")
    private String profileHref;

    @JsonProperty(value = "email")
    private String email;

    @Override
    public Long getUserId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getProfileHref() {
        return profileHref;
    }

    @Override
    public String getEmail() {
        return email;
    }
}
