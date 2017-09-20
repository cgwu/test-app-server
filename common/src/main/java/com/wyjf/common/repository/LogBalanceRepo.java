package com.wyjf.common.repository;

import com.wyjf.common.domain.LogBalance;
import com.wyjf.common.message.LogBalanceEx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */
public interface LogBalanceRepo extends JpaRepository<LogBalance, Long>,QueryDslPredicateExecutor<LogBalance> {

    public List<LogBalance> findByUidOrderByLogTimeDesc(long uid);

    public List<LogBalance> findByUidAndTypeOrderByLogTimeDesc(long uid, int type);

    @Query(name = "queryLogBalanceEx")
    public List<LogBalanceEx> findLogBalanceEx(long uid, int type, int offset, int length);

}
