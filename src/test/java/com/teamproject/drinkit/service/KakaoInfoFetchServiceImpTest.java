package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.SocialProviders;
import com.teamproject.drinkit.security.dto.SocialLoginDto;
import com.teamproject.drinkit.security.social.UserInfoFromSocial;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
public class KakaoInfoFetchServiceImpTest {
    private KakaoInfoFetchServiceImp kakaoInfoFetchServiceImp;

    private SocialLoginDto dto;
    private static final Logger log = LoggerFactory.getLogger(KakaoInfoFetchServiceImpTest.class);
    
    @Before
    public void setUp() throws Exception {
        String access_token = "9pD9n6H60E-pmab0k8CuNkAfcUmJ6aK_gmIDJAo8BVUAAAFlRixDVA";
        String user_id = "899845285";
        kakaoInfoFetchServiceImp = new KakaoInfoFetchServiceImp();
        dto = new SocialLoginDto(SocialProviders.KAKAO, access_token, user_id);
    }

    @Test
    public void USERINFO_FETCH_TEST() {
        UserInfoFromSocial userInfoFromSocial = kakaoInfoFetchServiceImp.fetchFromOAuthServer(dto);
        assertThat(userInfoFromSocial.getUsername(), is("배지원"));
        log.debug("profileHref: {}", userInfoFromSocial.getProfileHref());
    }
    

}