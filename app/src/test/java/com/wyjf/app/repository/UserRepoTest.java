package com.wyjf.app.repository;

import com.wyjf.common.domain.User;
import com.wyjf.common.repository.UserRepo;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/9/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepoTest {
    private static final Logger log = LoggerFactory.getLogger(UserRepoTest.class);

    @Autowired
    private UserRepo repo;

    @Autowired
    private UserRepo commonRepo;


    @Test
    public void testSave() {
        User user = new User();
        user.setPhone("188");
        user.setNickname("da-common");
        user.setBalance(0);
        user.setGender("M");
        user.setPasswordLogin("123");
        user.setPasswordTrade("456");
        repo.save(user);
        log.info("保存user成功!");
    }

//    @Test
//    public void testCommonSave() {
//        User user = new User();
//        user.setPhone("188");
//        user.setNickname("common");
//        user.setBalance(0);
//        user.setGender("M");
//        user.setPasswordLogin("123");
//        user.setPasswordTrade("456");
//        commonRepo.save(user);
//        log.info("保存user成功!");
//    }


}
