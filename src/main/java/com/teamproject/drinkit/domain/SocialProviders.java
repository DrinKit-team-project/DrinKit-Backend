package com.teamproject.drinkit.domain;

import com.teamproject.drinkit.security.social.UserInfoFromFacebook;
import com.teamproject.drinkit.security.social.UserInfoFromKakao;
import com.teamproject.drinkit.security.social.UserInfoFromSocial;
import org.springframework.stereotype.Component;

public enum SocialProviders {
    KAKAO("https://kapi.kakao.com/v2/user/me", UserInfoFromKakao.class),
    FACEBOOK("https://graph.facebook.com/v2.11/"
            , UserInfoFromFacebook.class);

    private String userInfoRequestUrl;
    private Class<? extends UserInfoFromSocial> userInfo;

    SocialProviders(String userInfoRequestUrl, Class<? extends UserInfoFromSocial> userInfo){
        this.userInfoRequestUrl = userInfoRequestUrl;
        this.userInfo = userInfo;
    }

    public String getUserInfoRequestUrl(){
        return userInfoRequestUrl;
    }

    public Class<? extends UserInfoFromSocial> getUserInfo(){ return userInfo;}

}
