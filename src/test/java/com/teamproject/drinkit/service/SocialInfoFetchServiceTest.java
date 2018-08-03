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
public class SocialInfoFetchServiceTest {
    private static final Logger log = LoggerFactory.getLogger(SocialInfoFetchService.class);
    private SocialInfoFetchService socialInfoFetchService;
    private SocialLoginDto socialLoginDto;

    @Before
    public void setUp() throws Exception {
     socialInfoFetchService = new SocialInfoFetchService();
     socialLoginDto = new SocialLoginDto(SocialProviders.KAKAO, "IHp21msn-Cxdsl-QmlLvdRd_2cb2KB7cYqHzlQo8BRIAAAFk-YS5tA");
    }

    @Test
    public void USER_INFO_FETCH_TEST() {
        UserInfoFromSocial userInfoFromSocial = socialInfoFetchService.fromOAuthServer(socialLoginDto);
        assertThat("배지원", is(userInfoFromSocial.getUsername()));
    }
}