package com.wyjf.common.repository;

import com.wyjf.common.domain.Draw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by Administrator on 2017/9/1.
 */
public interface DrawRepo extends JpaRepository<Draw, Long>,QueryDslPredicateExecutor<Draw> {

}
