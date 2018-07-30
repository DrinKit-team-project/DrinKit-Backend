package com.teamproject.drinkit.security;

import com.teamproject.drinkit.domain.Account;
import com.teamproject.drinkit.domain.AccountRepository;
import com.teamproject.drinkit.exception.NoSuchAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AccountDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserId(userId).orElseThrow(() -> new NoSuchAccountException("아이디에 해당하는 계정이 존재하지 않습니다."));
        return AccountDetails.fromAccountDomain(account);
    }


}
