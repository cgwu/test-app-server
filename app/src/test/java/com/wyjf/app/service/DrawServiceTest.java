package com.wyjf.app.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2017/9/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DrawServiceTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testProcess(){
//        jdbcTemplate.batchUpdate()
    }
}
