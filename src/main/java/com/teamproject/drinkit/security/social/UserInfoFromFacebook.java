package com.teamproject.drinkit.security.social;

import com.fasterxml.jackson.annotation.*;
import com.teamproject.drinkit.domain.Picture;

public class UserInfoFromFacebook implements UserInfoFromSocial {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "name")
    private String username;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "picture")
    private Picture picture;

    @Override
    public String getUserId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getProfileHref() {
        return picture.getData().getUrl();
    }

    @Override
    public String getEmail() {
        return email;
    }
}
