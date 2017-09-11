package com.wyjf.app.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wyjf.app.domainspec.UserSpec;
import com.wyjf.common.domain.QUser;
import com.wyjf.common.domain.User;
import com.wyjf.common.repository.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    @Test
    public void testJPACriteria() {
        Specification<User> searchSpec = UserSpec.phoneOrNicknameContainsIgnoreCase("132");
        PageRequest req = new PageRequest(1, 2, Sort.Direction.DESC, "uid");
//        List<User> searchResults = repo.findAll(searchSpec);
        Page<User> searchResults = repo.findAll(searchSpec, req);
        log.info("Number:{},总记录数:{},总页数:{}",searchResults.getNumber(), searchResults.getTotalElements(), searchResults.getTotalPages());
        for (User user : searchResults) {
            log.info(user.toString());
        }
    }

    @Test
    public void testQueryDsl() {
        QUser quser = QUser.user;
        Predicate predicate = quser.phone.eq("13288018543");// 根据用户名，查询user表
        User u = repo.findOne(predicate);
        log.info("按电话eq查找:{}", u);

        Predicate predicate2 = quser.phone.like("%132%").or(quser.nickname.like("%132%"));
//        Specification<User> searchSpec = UserSpec.phoneOrNicknameContainsIgnoreCase("132");
        PageRequest req = new PageRequest(0, 2, Sort.Direction.DESC, "uid");
        Page<User> searchResults = repo.findAll(predicate2, req);
        log.info("每页大小:{},页码:{},总记录数:{},总页数:{}",searchResults.getSize(),
                searchResults.getNumber(), searchResults.getTotalElements(), searchResults.getTotalPages());
        for (User user : searchResults) {
            log.info(user.toString());
        }
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
