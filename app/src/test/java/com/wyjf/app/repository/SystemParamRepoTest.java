package com.wyjf.app.repository;

import com.wyjf.app.service.SystemParamService;
import com.wyjf.common.domain.Draw;
import com.wyjf.common.domain.SystemParam;
import com.wyjf.common.repository.DrawRepo;
import com.wyjf.common.repository.SystemParamRepo;
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
public class SystemParamRepoTest {
    private static final Logger log = LoggerFactory.getLogger(SystemParamRepoTest.class);

    @Autowired
    private SystemParamRepo repo;

    @Autowired
    private SystemParamService service;

    @Test
    public void testSave() {
        SystemParam param = new SystemParam();
        param.setDataKey("STR_SystemName");
        param.setDataVal("无忧金融");
        param.setComment("系统名称");
        param.setSortId(0);
        param.setPid(0);
//        repo.save(param);
        service.saveOrUpdate(param);
        service.saveOrUpdate(param);

        log.info("保存成功!");
    }

    @Test
    public void testFindByKey() {
        SystemParam param =  repo.findByDataKey("STR_SystemName");
        log.info("param:{}",param);
    }
}
