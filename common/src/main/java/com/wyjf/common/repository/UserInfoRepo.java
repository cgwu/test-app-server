package com.wyjf.common.repository;

import com.wyjf.common.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/9/1.
 */
public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {

}
