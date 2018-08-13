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
        String access_token = "EAAM7HscfrTwBAGzE45ZBrynQPs7nEWbtg8qkc9ddsxcN1LniHbD3YeB1sMh13o1ZBcuKREIR80kT2trgHXekpA27nzgdoVBA03YDZC96xgySKBFVDoCzD4RZABvxn1dQAGRPhcssR9u1ji5QoZBxlFHyVmZC0vvaDBRu40NdF6iYH87IiJA8srtB9lIstiMqyU0H2BKqeLZAwZDZD";
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