package com.teamproject.drinkit.service;

import com.teamproject.drinkit.security.dto.SocialLoginDto;
import com.teamproject.drinkit.security.social.UserInfoFromSocial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FacebookInfoFetchServiceImp implements SocialInfoFetchService {
    private static final Logger log = LoggerFactory.getLogger(FacebookInfoFetchServiceImp.class);

    @Override
    public UserInfoFromSocial fetchFromOAuthServer(SocialLoginDto dto) {
        String baseUrl = dto.getProvider().getUserInfoRequestUrl();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(buildUrlComponents(dto, baseUrl), dto.getProvider().getUserInfo());
    }

    protected URI buildUrlComponents(SocialLoginDto dto, String baseUrl) {
        return UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("{id}")
                .queryParam("access_token", dto.getToken())
                .queryParam("fields", String.join(",", appendQueryParameters()))
                .build().expand(dto.getId()).toUri();
    }

    private List<String> appendQueryParameters() {
        List<String> fields = new ArrayList<>();
        fields.add("id");
        fields.add("name");
        fields.add("email");
        fields.add("picture");
        return fields;
    }
}
