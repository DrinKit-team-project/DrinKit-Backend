package com.teamproject.drinkit.security.token;

import com.teamproject.drinkit.domain.Account;
import com.teamproject.drinkit.security.AccountDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SocialPostAuthorizationToken extends UsernamePasswordAuthenticationToken {
    public SocialPostAuthorizationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public static SocialPostAuthorizationToken fromAccountDetails(AccountDetails accountDetails){
        return new SocialPostAuthorizationToken(accountDetails.getAccount(), accountDetails.getPassword(), accountDetails.getAuthorities());
    }

    public AccountDetails getAccountDetails(){
       return AccountDetails.fromAccountDomain((Account)super.getPrincipal());
    }
}
