package com.teamproject.drinkit.service;

import com.teamproject.drinkit.security.dto.SocialLoginDto;
import com.teamproject.drinkit.security.social.UserInfoFromSocial;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SocialInfoFetchService {

    private static final String HEADER_PREFIX = "Bearer ";

    public UserInfoFromSocial fromOAuthServer(SocialLoginDto dto){
        String userInfoReqUrl = dto.getProvider().getUserInfoRequestUrl();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>("parameters", generateHeader(dto.getToken()));
        return restTemplate.exchange(userInfoReqUrl, HttpMethod.GET, entity, dto.getProvider().getUserInfo()).getBody();
    }

    private HttpHeaders generateHeader(String token){
        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", generateHeaderContent(token));
        return header;
    }

    private String generateHeaderContent(String token){
        return HEADER_PREFIX + token;
    }
}
