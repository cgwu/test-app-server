package com.wyjf.app.repository;

import com.wyjf.common.domain.Ticket;
import com.wyjf.common.message.LogBalanceEx;
import com.wyjf.common.repository.LogBalanceRepo;
import com.wyjf.common.repository.TicketRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogBalanceRepoTest {
    private static final Logger log = LoggerFactory.getLogger(LogBalanceRepoTest.class);

    @Autowired
    private LogBalanceRepo repo;

    @Test
    public void testFind() {
        List<LogBalanceEx> list = repo.findLogBalanceEx(18, 0, 0, 10);
        for (LogBalanceEx b : list) {
//            log.info("log id :{}, logTime:{}, draw day:{}", b.getLid(), b.getLogTime(), b.getDrawDay());
            log.info(b.toString());
        }
    }

}
