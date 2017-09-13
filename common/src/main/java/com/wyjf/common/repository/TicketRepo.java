package com.wyjf.common.repository;

import com.wyjf.common.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by Administrator on 2017/9/1.
 */
public interface TicketRepo extends JpaRepository<Ticket, Long>, QueryDslPredicateExecutor<Ticket> {

//    @Procedure("sp_buy_ticket")
//    public int buy(int a, int b);

}
