package com.wyjf.common.repository;

import com.wyjf.common.domain.SystemParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by Administrator on 2017/9/7.
 */
public interface SystemParamRepo extends JpaRepository<SystemParam, Long>,QueryDslPredicateExecutor<SystemParam> {
    public SystemParam findByDataKey(String key);
}
