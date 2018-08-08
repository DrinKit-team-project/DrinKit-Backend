package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.SocialProviders;
import com.teamproject.drinkit.security.dto.SocialLoginDto;
import com.teamproject.drinkit.security.social.UserInfoFromFacebook;
import com.teamproject.drinkit.security.social.UserInfoFromSocial;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FacebookInfoFetchServiceImpTest {
    private FacebookInfoFetchServiceImp facebookInfoFetchServiceImp;
    private SocialLoginDto dto;
    private static final Logger log = LoggerFactory.getLogger(FacebookInfoFetchServiceImpTest.class);


    @Before
    public void setUp() throws Exception {
        String access_token = "EAAM7HscfrTwBAIfKPd73VW23TAij7gjh1a1MtvI9y9o7hxnLUGM0ZBdZCKEZBLFnZBCi9FFMAP6RAHvLH8llZCADXTwIYuvmne4eWVGJ1mpmhZC5jKBB1b3nBAcDRR8VS6zxxJPm9yyZCyHGSjCZAqG6oQtYUyELxVFtquZBDGJDzOzoXQ5SspM8fIM7ZAN8XPrE3escKAmBY3ZAQZDZD";
        String user_id = "704798269891295";
        facebookInfoFetchServiceImp = new FacebookInfoFetchServiceImp();
        dto = new SocialLoginDto(SocialProviders.FACEBOOK, access_token, user_id);
    }

    @Test
    public void QUERY_PARARMETER_APPEND_TEST() {
        String url = facebookInfoFetchServiceImp.buildUrlComponents(dto, "https://graph.facebook.com/v3.1/{user_id}").toUriString();
        log.debug("url : {}", url);
    }
    @Test
    public void USER_ID_APPEND_TEST() {
        UriComponentsBuilder builder = facebookInfoFetchServiceImp.buildUrlComponents(dto, "https://graph.facebook.com/v3.1/{user_id}");
        String url = facebookInfoFetchServiceImp.appendUserId(builder, dto).toString();
        log.debug("url: {}", url);
    }

    @Test
    public void USERINFO_FETCH_TEST() {
        UserInfoFromSocial userInfoFromSocial = facebookInfoFetchServiceImp.fetchFromOAuthServer(dto);
        assertThat(userInfoFromSocial.getUsername(), is("Jiwon Bae"));
    }
}