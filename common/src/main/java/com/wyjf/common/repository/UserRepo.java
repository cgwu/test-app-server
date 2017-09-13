package com.wyjf.common.repository;

import com.wyjf.common.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

/**
 * Created by Administrator on 2017/8/22.
 */
public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>,QueryDslPredicateExecutor<User> {

    public User findByNickname(String name);

    public User findByPhone(String phone);

    @Query(value = "SELECT tu FROM User tu WHERE tu.phone =:name AND tu.passwordLogin = :pwd")
    public User findByPhoneOrPwd(@Param("name") String phone, @Param("pwd") String pwd);

    @Query(value = "SELECT tu FROM User tu WHERE tu.token =:token AND tu.tokenTime > NOW()")
    public User findByTokenOrTime(@Param("token") String token);

    @Modifying
    @Query(value="update user set balance = balance + :amt where uid = :uid", nativeQuery = true)
    public void addBalance(@Param("uid") long uid, @Param("amt") double amt);
}
