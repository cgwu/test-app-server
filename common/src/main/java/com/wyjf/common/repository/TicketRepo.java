package com.wyjf.common.repository;

import com.wyjf.common.domain.Ticket;
import com.wyjf.common.message.MyTicket;
import com.wyjf.common.message.BasicTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */
public interface TicketRepo extends JpaRepository<Ticket, Long>, QueryDslPredicateExecutor<Ticket> {

//    @Procedure("sp_buy_ticket")
//    public int buy(int a, int b);

    /* 调用命名查询，返回非托管实体 */
    @Query(name = "queryTicketByDraw")
    public List<BasicTicket> findBaseTicket(long drawId, int offset, int length);

    @Query(name = "queryMine")
    public List<MyTicket> findMyTicket(long drawId);

}
