package com.teamproject.drinkit.security.token;

import com.teamproject.drinkit.security.dto.SocialLoginDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class SocialPreAuthorizationToken extends UsernamePasswordAuthenticationToken {
    public SocialPreAuthorizationToken(SocialLoginDto socialLoginDto){
        super(socialLoginDto.getProvider(), socialLoginDto.getToken());
    }
}
