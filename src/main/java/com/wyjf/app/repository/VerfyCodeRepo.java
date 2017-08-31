package com.wyjf.app.repository;

import com.wyjf.app.domain.LogVerifycode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Administrator on 2017/8/31 0031.
 */
public interface VerfyCodeRepo extends JpaRepository<LogVerifycode, Long> {
    /**
     * 查找一个电话号码，在10分钟之内是否存在有效验证码
     * @param phone
     * @return
     */
    @Query(value = "SELECT t FROM VerfyCode t WHERE t.phone = :phone AND t.status = '0' AND (UNIX_TIMESTAMP(NOW())-UNIX_TIMESTAMP(t.createTime))/60 <= 10")
    public LogVerifycode findByPhoneExist(@Param("phone") String phone);
}
