package com.teamproject.drinkit.support.test;

import com.teamproject.drinkit.security.dto.JwtDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

@TestPropertySource(locations = "classpath:/test.properties")
public class AuthenticationTestSupporter {
    private static final String JWT_TOKEN_REQUEST_JSON_STRING = "{\"provider\":\"KAKAO\",\"id\":\"889316482\", \"token\":\"dPMT59pHqIxKCZ5lkbuFwinZPNceqmxoO4fiEQoqAq8AAAFlmr1JrQ\"}";
    private static final String JWT_TOKEN_REQUEST_URL = "http://localhost:8080/social";

    private static final Logger log = LoggerFactory.getLogger(AuthenticationTestSupporter.class);

    public static String requestJwtToken() {
        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(JWT_TOKEN_REQUEST_JSON_STRING, headers);
        ResponseEntity<JwtDto> jwtTokenResponse = template.postForEntity(JWT_TOKEN_REQUEST_URL, request, JwtDto.class);
        return jwtTokenResponse.getBody().getToken();
    }

    private static HttpHeaders buildAuthorizationHeader(String jwtToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + jwtToken);
        return headers;
    }

    public static HttpEntity buildRequestEntityForGet() {
        return new HttpEntity(buildAuthorizationHeader(requestJwtToken()));
    }

}
