package com.wyjf.app.repository;

import com.alibaba.fastjson.JSON;
import com.wyjf.common.domain.Draw;
import com.wyjf.common.domain.User;
import com.wyjf.common.repository.DrawRepo;
import com.wyjf.common.repository.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DrawRepoTest {
    private static final Logger log = LoggerFactory.getLogger(DrawRepoTest.class);

    @Autowired
    private DrawRepo repo;

    @Test
    public void testSave() {
        Draw draw = new Draw();
        draw.setDrawDay(LocalDate.now());
        draw.setDrawSeq(1);
        draw.setStartDate(LocalDateTime.now().minusHours(5));
        draw.setEndDate(LocalDateTime.now());
//        draw.setEndDate(Instant.now().minusSeconds(60*5));
        draw.setAmountUp(100);
        draw.setAmountDown(200);
        draw.setStatus(0);
        repo.save(draw);
        log.info("保存成功!");
    }

    @Test
    public void testGet(){
        Draw d =  repo.findOne(1L);
        log.info(d.toString());
        log.info(JSON.toJSONString(d));
    }

}
