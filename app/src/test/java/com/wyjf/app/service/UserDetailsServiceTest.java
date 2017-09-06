package com.wyjf.app.service;

import com.wyjf.common.domain.Draw;
import com.wyjf.common.repository.DrawRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailsServiceTest {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceTest.class);

    @Autowired
    private PasswordEncoder encoder;


    @Test
    public void testPasswordEncoder() {
        String encpwd = encoder.encodePassword("123",null);
        log.info(encpwd);
    }

}
