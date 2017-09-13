package com.wyjf.app.service;

import com.wyjf.common.domain.Ticket;
import com.wyjf.common.repository.TicketRepo;
import com.wyjf.common.util.CommonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/9/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest {
    private static final Logger log = LoggerFactory.getLogger(TicketServiceTest.class);

    @Autowired
    private TicketService ticketService;

    @Test
    public void testSave() {
        Ticket ticket  = new Ticket();
        ticket.setSid(CommonUtil.getSerialNO());
        ticket.setUid(1L);
        ticket.setDid(1L);
        ticket.setDirection(1);
        ticket.setAmount(100);
        ticket.setRealAmount(100);
        ticket.setBuyTime(LocalDateTime.now());
        ticket.setStatus(1);
        ticketService.buy(ticket);
        log.info("测试买票成功!");
    }

}
