package com.wyjf.app.repository;

import com.wyjf.app.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/8/22.
 */
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    public UserEntity findByName(String name);
}
