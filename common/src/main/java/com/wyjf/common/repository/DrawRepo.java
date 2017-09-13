package com.wyjf.common.repository;

import com.wyjf.common.domain.Draw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */
public interface DrawRepo extends JpaRepository<Draw, Long>, QueryDslPredicateExecutor<Draw> {

    public List<Draw> findByDrawDay(LocalDate drawDay);

    public List<Draw> findByDrawDayAndDrawSeq(LocalDate drawDay, int drawSeq);

    @Modifying
    @Query(value = "update draw set amount_up = amount_up + :amount where did = :did", nativeQuery = true)
    public void buyAmountUp(@Param("did") long did, @Param("amount") double amount);

    @Modifying
    @Query(value = "update draw set amount_down = amount_down + :amount where did = :did", nativeQuery = true)
    public void buyAmountDown(@Param("did") long did, @Param("amount") double amount);

}
