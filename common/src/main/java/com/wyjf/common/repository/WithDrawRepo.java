package com.wyjf.common.repository;

import com.wyjf.common.domain.BankCard;
import com.wyjf.common.domain.WithDraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

/**
 * Created by Administrator on 2017/9/21 0021.
 */
public interface WithDrawRepo extends JpaRepository<WithDraw, Long>, QueryDslPredicateExecutor<WithDraw> {
    public List<WithDraw> findByUid(Long uId);
}
