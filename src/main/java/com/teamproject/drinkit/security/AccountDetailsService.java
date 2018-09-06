package com.teamproject.drinkit.security;

import com.teamproject.drinkit.domain.*;
import com.teamproject.drinkit.exception.NoLoginedUserException;
import com.teamproject.drinkit.exception.NoSuchAccountException;
import com.teamproject.drinkit.exception.NoSuchMenuException;
import com.teamproject.drinkit.security.dto.SocialLoginDto;
import com.teamproject.drinkit.security.jwt.JwtDecoder;
import com.teamproject.drinkit.security.jwt.JwtExtractor;
import com.teamproject.drinkit.security.social.UserInfoFromSocial;
import com.teamproject.drinkit.service.KakaoInfoFetchServiceImp;
import com.teamproject.drinkit.service.SocialFetchServiceFactory;
import com.teamproject.drinkit.service.SocialInfoFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AccountDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SocialFetchServiceFactory socialFetchServiceFactory;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private JwtDecoder decoder;

    public Account search(SocialLoginDto dto){
        UserInfoFromSocial userInfo = socialFetchServiceFactory.fetch(dto);
        return accountRepository.findBySocialIdAndSocialProvider(userInfo.getUserId(), dto.getProvider())
                .orElseGet(() -> accountRepository.save(new Account("jiwon", userInfo.getUsername(), "1234", UserRole.USER, userInfo.getUserId(), dto.getProvider(), userInfo.getProfileHref())));
    }
    public Account editNickname(String header, String newNickname){
        Account logined = decoder.findLoginedUser(header);
        return accountRepository.save(logined.editNickname(logined, newNickname));
    }

    public Account addFavoriteMenu(Long id, Long menuId) {
        Account logined = accountRepository.findById(id).orElseThrow(() -> new NoSuchAccountException("아이디에 해당하는 계정이 존재하지 않습니다."));
        Menu favoriteMenu = menuRepository.findById(menuId).orElseThrow(() -> new NoSuchMenuException("해당하는 메뉴가 없습니다."));
        return accountRepository.save(logined.addFavoriteMenu(favoriteMenu));
    }

    public Account removeFavoriteMenu(Long id, Long menuId) {
        Account logined = accountRepository.findById(id).orElseThrow(() -> new NoSuchAccountException("아이디에 해당하는 계정이 존재하지 않습니다."));
        Menu favoriteMenu = menuRepository.findById(menuId).orElseThrow(() -> new NoSuchMenuException("해당하는 메뉴가 없습니다."));
        return accountRepository.save(logined.removeFavoriteMenu(favoriteMenu));
    }

    public Iterable<Menu> findFavoriteMenus(Long id) {
        Account logined = accountRepository.findById(id).orElseThrow(() -> new NoSuchAccountException("아이디에 해당하는 계정이 존재하지 않습니다."));
        return menuRepository.findByAccountsContaining(logined).orElseThrow(() -> new NoSuchMenuException("사용자에 등록된 메뉴들이 존재하지 않습니다."));
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserId(userId).orElseThrow(() -> new NoSuchAccountException("아이디에 해당하는 계정이 존재하지 않습니다."));
        return AccountDetails.fromAccountDomain(account);
    }

}
