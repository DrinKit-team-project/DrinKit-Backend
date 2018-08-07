package com.teamproject.drinkit.security.provider;

import com.teamproject.drinkit.security.AccountDetails;
import com.teamproject.drinkit.security.jwt.JwtDecoder;
import com.teamproject.drinkit.security.token.PostAuthorizationToken;
import com.teamproject.drinkit.security.token.PreAuthenticationJwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PreAuthenticationJwtToken preToken = (PreAuthenticationJwtToken)authentication;
        return PostAuthorizationToken.fromAccountDetails(jwtDecoder.decode(preToken.getToken()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PreAuthenticationJwtToken.class.isAssignableFrom(authentication);
    }
}
