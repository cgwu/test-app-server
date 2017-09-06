package com.wyjf.app.repository;

import com.wyjf.common.domain.Draw;
import com.wyjf.common.repository.DrawRepo;
import com.wyjf.common.repository.StockDataRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StockDataRepoTest {
    private static final Logger log = LoggerFactory.getLogger(StockDataRepoTest.class);

    @Autowired
    private StockDataRepo repo;


    @Test
    public void testQuery() {
        List list = repo.findSockDataMin("2017/09/06");
        log.info("list count:{}, {}",list.size(), list);

    }

}
