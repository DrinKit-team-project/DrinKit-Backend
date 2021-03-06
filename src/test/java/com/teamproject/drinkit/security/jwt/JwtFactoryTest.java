package com.teamproject.drinkit.security.jwt;

import com.teamproject.drinkit.domain.Account;
import com.teamproject.drinkit.domain.SocialProviders;
import com.teamproject.drinkit.domain.UserRole;
import com.teamproject.drinkit.security.AccountDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:/test.properties")
public class JwtFactoryTest {
    private static final Logger log = LoggerFactory.getLogger(JwtFactoryTest.class);
    private AccountDetails accountDetails;

    @Autowired
    private JwtFactory jwtFactory;

    @Before
    public void setUp() throws Exception {
        Account account = new Account("username", "jiwon", "1234", UserRole.USER, null, SocialProviders.KAKAO, null);
        accountDetails = AccountDetails.fromAccountDomain(account);
    }

    @Test
    public void TOKEN_GENERATE_TEST() {
        log.error("token: {}", jwtFactory.generateFrom(accountDetails));
    }
}