package com.wyjf.app.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2017/9/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DrawServiceTest {
    private static final Logger log = LoggerFactory.getLogger(DrawServiceTest.class);

    @Autowired
    private DrawResultService service;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testProcess(){
        Pair<Integer,String> result = service.process(13);
        log.info("code:{},msg:{}", result.getFirst(), result.getSecond());
    }
}
