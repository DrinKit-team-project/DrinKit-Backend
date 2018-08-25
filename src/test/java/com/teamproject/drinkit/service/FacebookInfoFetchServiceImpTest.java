package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.SocialProviders;
import com.teamproject.drinkit.security.dto.SocialLoginDto;
import com.teamproject.drinkit.security.social.UserInfoFromSocial;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FacebookInfoFetchServiceImpTest {
    private FacebookInfoFetchServiceImp facebookInfoFetchServiceImp;
    private SocialLoginDto dto;
    private static final Logger log = LoggerFactory.getLogger(FacebookInfoFetchServiceImpTest.class);

    @Before
    public void setUp() throws Exception {
<<<<<<< HEAD
        String access_token = "EAAM7HscfrTwBAGJfGaBj0iqsIMlpAjjMUA6DdQtVZCT2mb9TY4vAg1YWXegarFZApj83enYGoNSVuwIdGC3ZAVaAaxFb5sXmNV4rnj1lw8kCGkXGzqbiv6ogLDSZAtziT8sgEJR5EBWn3hEN2NlL7cGe8cAWpyh5yGeyk04SqvUWtfQJCMqImZAQfDBYz9ZBKmOlZBObxwemgZDZD";
=======
        String access_token = "EAAM7HscfrTwBAE02KrZCh59A1ccYwgAru7BCIRTT4WbU5Umr9zuMyANA7LvDkWehqMsW0fDzfMqpCBDJfkUqRrU3jDNeHKWhNNtjLwvazyPmRiVNCuYzvDjCuZAgDiLjV6vtU9ZAAMhiYadN8AmxbFSWqwEj3QwQCexZAkl9PEI5NJu3LgRAhMjcMhcExdrKny12FYxO1gZDZD";
>>>>>>> 18dfa423579d2be433f3a56e1936ab5f1b101559
        String user_id = "704798269891295";
        facebookInfoFetchServiceImp = new FacebookInfoFetchServiceImp();
        dto = new SocialLoginDto(SocialProviders.FACEBOOK, access_token, user_id);
    }

    @Test
    public void USERINFO_FETCH_TEST() {
        UserInfoFromSocial userInfoFromSocial = facebookInfoFetchServiceImp.fetchFromOAuthServer(dto);
        assertThat(userInfoFromSocial.getUsername(), is("Jiwon Bae"));
    }
}