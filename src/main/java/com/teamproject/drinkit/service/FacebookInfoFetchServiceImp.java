package com.teamproject.drinkit.service;

import com.teamproject.drinkit.security.dto.SocialLoginDto;
import com.teamproject.drinkit.security.social.UserInfoFromSocial;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacebookInfoFetchServiceImp implements SocialInfoFetchService {
    private static final String QUERY_PARAMS = "?fields=id,name,email,picture";

    @Override
    public UserInfoFromSocial fetchFromOAuthServer(SocialLoginDto dto) {
        String baseUrl = dto.getProvider().getUserInfoRequestUrl() + QUERY_PARAMS;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(appendUserId(buildUrlComponents(dto, baseUrl), dto), dto.getProvider().getUserInfo()).getBody();
    }

    protected UriComponentsBuilder buildUrlComponents(SocialLoginDto dto, String baseUrl) {
        return UriComponentsBuilder
                .fromUriString(baseUrl)
                .queryParam("access_token", dto.getToken());
    }

    public URI appendUserId(UriComponentsBuilder builder, SocialLoginDto dto){
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("user_id", dto.getId());
        return builder.buildAndExpand(urlParams).toUri();
    }

}
