package com.wyjf.common.repository;

import com.wyjf.common.domain.Draw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */
public interface DrawRepo extends JpaRepository<Draw, Long>,QueryDslPredicateExecutor<Draw> {

    public List<Draw> findByDrawDay(LocalDate drawDay);

    public List<Draw> findByDrawDayAndDrawSeq(LocalDate drawDay, int drawSeq);

}
