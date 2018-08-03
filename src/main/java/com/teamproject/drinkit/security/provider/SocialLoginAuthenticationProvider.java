package com.teamproject.drinkit.security.provider;

import com.teamproject.drinkit.domain.Account;
import com.teamproject.drinkit.domain.AccountRepository;
import com.teamproject.drinkit.security.AccountDetails;
import com.teamproject.drinkit.security.AccountDetailsService;
import com.teamproject.drinkit.security.social.UserInfoFromSocial;
import com.teamproject.drinkit.security.token.SocialPostAuthorizationToken;
import com.teamproject.drinkit.security.token.SocialPreAuthorizationToken;
import com.teamproject.drinkit.service.SocialInfoFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class SocialLoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AccountDetailsService accountDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SocialPreAuthorizationToken preToken = (SocialPreAuthorizationToken) authentication;
        Account account = accountDetailsService.search(preToken.getSocialLoginDto());
        return SocialPostAuthorizationToken.fromAccountDetails(AccountDetails.fromAccountDomain(account));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SocialPreAuthorizationToken.class.isAssignableFrom(authentication);
    }
}
