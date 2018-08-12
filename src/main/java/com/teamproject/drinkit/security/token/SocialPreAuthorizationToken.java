package com.teamproject.drinkit.security.token;

import com.teamproject.drinkit.security.dto.SocialLoginDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class SocialPreAuthorizationToken extends UsernamePasswordAuthenticationToken {
    private SocialLoginDto socialLoginDto;

    public SocialPreAuthorizationToken(SocialLoginDto socialLoginDto){
        super(socialLoginDto.getProvider(), socialLoginDto);
        this.socialLoginDto = socialLoginDto;
    }

    public SocialLoginDto getSocialLoginDto(){
        return socialLoginDto;
    }
}
