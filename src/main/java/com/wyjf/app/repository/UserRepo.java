package com.wyjf.app.repository;

import com.wyjf.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */
public interface UserRepo extends JpaRepository<User, Long> {

    public User findByNickname(String name);

    @Query(value = "SELECT tu FROM User tu WHERE tu.nickname =:name AND tu.passwordLogin = :pwd")
    public List<User> findByPhoneOrPwd(@Param("name") String phone, @Param("pwd") String pwd);

}
