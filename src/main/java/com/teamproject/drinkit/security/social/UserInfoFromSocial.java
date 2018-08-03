package com.teamproject.drinkit.security.social;

public interface UserInfoFromSocial {
    Long getUserId();

    String getUsername();

    String getProfileHref();

    String getEmail();
}
