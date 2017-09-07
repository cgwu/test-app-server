package com.wyjf.common.repository;

import com.wyjf.common.domain.SystemParam;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/9/7.
 */
public interface SystemParamRepo extends JpaRepository<SystemParam, Long> {
    public SystemParam findByDataKey(String key);
}
