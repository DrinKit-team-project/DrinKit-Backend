package com.teamproject.drinkit.support.test;

import com.teamproject.drinkit.security.dto.JwtDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AuthenticationTestSupporter {

    private static final String JWT_TOKEN_REQUEST_URL = "http://localhost:8080/social";
    private static final Logger log = LoggerFactory.getLogger(AuthenticationTestSupporter.class);

    private static String requestJwtToken() {
        RestTemplate template = new RestTemplate();
        HttpEntity<String> request = AuthenticationTestSupporter.buildHttpEntity();
        ResponseEntity<JwtDto> jwtTokenResponse = template.postForEntity(JWT_TOKEN_REQUEST_URL, request, JwtDto.class);
        return jwtTokenResponse.getBody().getToken();
    }

    private static HttpHeaders buildAuthorizationHeader(String jwtToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + jwtToken);
        return headers;
    }

    private static HttpEntity<String> buildHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json = "{\"provider\":\"KAKAO\",\"id\":\"889316482\", \"token\":\"dHNL_6ZZL9WaaL0-SaJ5pti3kbj3IEanl1-C1woqAucAAAFleP7GXA\"}";
        return new HttpEntity<>(json, headers);
    }

    public static HttpEntity buildRequestEntity() {
        return new HttpEntity(buildAuthorizationHeader(requestJwtToken()));
    }
}
