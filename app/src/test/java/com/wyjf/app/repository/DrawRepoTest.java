package com.wyjf.app.repository;

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
        draw.setDid("201709015");
        draw.setStartDate(new Timestamp(new Date().getTime()));
        draw.setEndDate(new Timestamp(new Date().getTime() + 1000 * 6));
        draw.setAmountUp(100);
        draw.setAmountDown(200);
        draw.setStatus(0);
        repo.save(draw);
        log.info("保存成功!");
    }

}
