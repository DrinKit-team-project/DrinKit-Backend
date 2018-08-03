package com.teamproject.drinkit.security;

import com.teamproject.drinkit.domain.Account;
import com.teamproject.drinkit.domain.UserRole;
import com.teamproject.drinkit.security.token.SocialPostAuthorizationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDetails extends User {
    private Account account;

    private AccountDetails(Account account, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.account = account;
    }

    private AccountDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static AccountDetails fromAccountDomain(Account account){
        return new AccountDetails(account, account.getUserId(), account.getPassword(), parseAuthorities(account.getUserRole()));
    }

    private static List<SimpleGrantedAuthority> parseAuthorities(UserRole userRole){
        return Arrays.asList(userRole).stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }



    public Account getAccount() {
        return account;
    }
}
