package com.teamproject.drinkit.service;

import com.teamproject.drinkit.security.dto.SocialLoginDto;
import com.teamproject.drinkit.security.social.UserInfoFromSocial;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class KakaoInfoFetchServiceImp implements SocialInfoFetchService {

    private static final String HEADER_PREFIX = "Bearer ";

    @Override
    public UserInfoFromSocial fetchFromOAuthServer(SocialLoginDto dto){
        String userInfoReqUrl = dto.getProvider().getUserInfoRequestUrl();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>("parameters", generateHeader(dto.getToken()));
        return restTemplate.exchange(userInfoReqUrl, HttpMethod.GET, entity, dto.getProvider().getUserInfo()).getBody();
    }

    public HttpHeaders generateHeader(String token){
        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", generateHeaderContent(token));
        return header;
    }
    public String generateHeaderContent(String token){
        return HEADER_PREFIX + token;
    }

}
