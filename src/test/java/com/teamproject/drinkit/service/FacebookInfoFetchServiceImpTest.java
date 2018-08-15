package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.SocialProviders;
import com.teamproject.drinkit.security.dto.SocialLoginDto;
import com.teamproject.drinkit.security.social.UserInfoFromSocial;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FacebookInfoFetchServiceImpTest {
    private FacebookInfoFetchServiceImp facebookInfoFetchServiceImp;
    private SocialLoginDto dto;
    private static final Logger log = LoggerFactory.getLogger(FacebookInfoFetchServiceImpTest.class);

    @Before
    public void setUp() throws Exception {
        String access_token = "EAAM7HscfrTwBAFq4RKZCS7Slr3AG7ZCLMkn87HdFzTmh2hUypEbUZCwdv0cpgq8zS3lyjjyH7v9bu6hndB9EjZAKsNZBiK76ZC4m5M67OjpbXIYym9ubHq1jzmstWI68QuX1nCKAtuYvzNVmZCkZCpF6GbO3Oi7k6OSDiZAK2XGbpzssaoZAR3ePwBRWSfrvTvCft1vQb8UyBabgZDZD";
        String user_id = "704798269891295";
        facebookInfoFetchServiceImp = new FacebookInfoFetchServiceImp();
        dto = new SocialLoginDto(SocialProviders.FACEBOOK, access_token, user_id);
    }

    @Test
    public void USERINFO_FETCH_TEST() {
        UserInfoFromSocial userInfoFromSocial = facebookInfoFetchServiceImp.fetchFromOAuthServer(dto);
        assertThat(userInfoFromSocial.getUsername(), is("Jiwon Bae"));
    }
}