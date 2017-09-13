package com.wyjf.app.repository;

import com.wyjf.common.domain.Draw;
import com.wyjf.common.domain.Ticket;
import com.wyjf.common.repository.DrawRepo;
import com.wyjf.common.repository.TicketRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketRepoTest {
    private static final Logger log = LoggerFactory.getLogger(TicketRepoTest.class);

    @Autowired
    private TicketRepo repo;

    @Test
    public void testSave() {
        Ticket ticket  = new Ticket();
        ticket.setDid(1L);
        ticket.setStatus(1);
        ticket.setAmount(123.315);
        ticket.setRealAmount(100);
        ticket.setDirection(0);
        ticket.setUid(6606);

        repo.save(ticket);
        log.info("保存测试票成功!");
    }

    @Test
    public void testBuy() {
        int ret = repo.buy(6,3);
        log.info("ret:{}",ret);
    }

}
