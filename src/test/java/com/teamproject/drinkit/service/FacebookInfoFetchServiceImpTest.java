package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.SocialProviders;
import com.teamproject.drinkit.security.dto.SocialLoginDto;
import com.teamproject.drinkit.security.social.UserInfoFromSocial;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@TestPropertySource(locations = "classpath:/test.properties")
public class FacebookInfoFetchServiceImpTest {
    private FacebookInfoFetchServiceImp facebookInfoFetchServiceImp;
    private SocialLoginDto dto;
    private static final Logger log = LoggerFactory.getLogger(FacebookInfoFetchServiceImpTest.class);

    @Before
    public void setUp() throws Exception {

        String access_token = "EAAM7HscfrTwBABZBjfMINhvAxxNc2pGfjjZCFMhu9x02nv3utDUq33yj9HBqAGNWTr7oGif3ad1yXqKhdwiyLKcLIBHu4tZCcWKO5MQSVtga3oGiJgjPSF1Ctifq5bU9hng9DUoZBnwmtZBSeZAtiPO7ECEeKzU7ZCzOzEgEJ6w9sxPY5AtTt90sPAoAteCyR9aZCDiAhwCnJQZDZD" +
                "";
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