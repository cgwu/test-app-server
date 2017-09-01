package com.wyjf.common.repository;

import com.wyjf.common.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/9/1.
 */
public interface TicketRepo extends JpaRepository<Ticket, Long> {

}
