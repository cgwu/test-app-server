package com.wyjf.app.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.DoubleHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Administrator on 2017/9/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StockDataServiceTest {
    private static final Logger log = LoggerFactory.getLogger(StockDataServiceTest.class);

    @Autowired
    private StockDataService stockDataService;

    static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    public void findData() {
        Double data = stockDataService.getData(LocalDateTime.parse("2017-09-21 09:30:00", format));
        log.info("data:{}", data);

        data = stockDataService.getData(LocalDateTime.parse("1917-09-21 09:30:00", format));
        log.info("data:{}", data);

    }

}
