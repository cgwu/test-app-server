package com.wyjf.app.service;

import com.wyjf.common.domain.SystemParam;
import com.wyjf.common.repository.SystemParamRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/9/7.
 */
@Service
public class SystemParamService {
    private static final Logger log = LoggerFactory.getLogger(SystemParamService.class);

    @Autowired
    private SystemParamRepo repo;

    public void saveOrUpdate(SystemParam pm) {
        SystemParam model = repo.findByDataKey(pm.getDataKey());
        if (model == null) repo.save(pm);
        else {
            model.setDataKey(pm.getDataKey());
            model.setDataVal(pm.getDataVal());
            model.setComment(pm.getComment());
            model.setSortId(pm.getSortId());
            model.setPid(pm.getPid());
            repo.save(model);
        }
    }

    public SystemParam findByKey(String key) {
        return repo.findByDataKey(key);
    }

    /**
     * 获取开盘前多少分钟可投注设置
     * @return
     */
    public int getBeforeBuyMins() {
        int beforeBuyMins = 0;
        try {
            SystemParam param = repo.findByDataKey("INT_BeforeBuyMins");
            if (param != null) {
                beforeBuyMins = Integer.parseInt(param.getDataVal());
            }
        } catch (Exception e) {
            log.error("Find INT_BeforeBuyMins Exception: {}", e.getMessage());
        }
        return beforeBuyMins;
    }

}
