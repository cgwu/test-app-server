package com.wyjf.common.repository;


import com.wyjf.common.domain.DrawResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by Administrator on 2017/9/1.
 */
public interface DrawResultRepo extends JpaRepository<DrawResult, Long>, QueryDslPredicateExecutor<DrawResult> {

}
