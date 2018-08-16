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
        String access_token = "EAAM7HscfrTwBAHmUqpVkmfCcr8IG7AekN1VQ2iiUIT9qXyXkUgw5hIWBHnzhNSYM2hJsYwKEEJwrXmtRANxiuTCwXfWNagTuvtNKB8JDAPayoyLLHKcPgm1ZActxmwZAXsUq1XyAg2ZCBjOQ93QpynhcS1z80fE809BqvZCZAn5gHhLd9ZBovpNqEjJ42ug9AGEPZBGRiFAwgZDZD";
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