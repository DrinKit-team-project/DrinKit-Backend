package com.teamproject.drinkit.service;

import com.teamproject.drinkit.security.dto.SocialLoginDto;
import com.teamproject.drinkit.security.social.UserInfoFromSocial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialFetchServiceFactory {

    @Autowired
    private KakaoInfoFetchServiceImp kakaoInfoFetchServiceImp;

    @Autowired
    private FacebookInfoFetchServiceImp facebookInfoFetchServiceImp;

    public UserInfoFromSocial fetch(SocialLoginDto dto) {
        if(dto.isFromFacebook()){
            return facebookInfoFetchServiceImp.fetchFromOAuthServer(dto);
        }
        return kakaoInfoFetchServiceImp.fetchFromOAuthServer(dto);
    }
}
