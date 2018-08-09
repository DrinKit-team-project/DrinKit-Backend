package com.teamproject.drinkit.security.social;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class UserInfoFromKakao implements UserInfoFromSocial {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "properties")
    private Map<String, String> properties;

    @JsonProperty(value= "kakao_account")
    private Map<String, String> accountInfo;

    @Override
    public String getUserId() {
        return id;
    }

    @Override
    public String getUsername() {
        return properties.get("nickname");
    }

    @Override
    public String getProfileHref() {
        return properties.get("profile_image");
    }

    @Override
    public String getEmail() {
        return accountInfo.get("email");
    }
}
