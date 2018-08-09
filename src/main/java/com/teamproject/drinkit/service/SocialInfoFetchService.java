package com.teamproject.drinkit.service;

import com.teamproject.drinkit.security.dto.SocialLoginDto;
import com.teamproject.drinkit.security.social.UserInfoFromSocial;

public interface SocialInfoFetchService {
    UserInfoFromSocial fetchFromOAuthServer(SocialLoginDto dto);
}
