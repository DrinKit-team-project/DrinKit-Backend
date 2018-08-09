package com.teamproject.drinkit.security.dto;

import com.teamproject.drinkit.domain.SocialProviders;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SocialLoginDtoTest {
    private SocialLoginDto socialLoginDto;

    @Before
    public void setUp() throws Exception {
        socialLoginDto = new SocialLoginDto(SocialProviders.FACEBOOK, null, null);
    }

    @Test
    public void IS_FROM_FACEBOOK_TEST() {
        assertTrue(socialLoginDto.isFromFacebook());
    }
}