package com.wyjf.job.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wyjf.common.domain.StockData;
import com.wyjf.common.repository.StockDataRepo;
import com.wyjf.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/8/17 0017.
 */


@Component
public class ScheduledUtil {
    private Logger logger = Logger.getLogger(ScheduledUtil.class);
    //@Scheduled(cron="0 0/2 8-20 * * ?")
    @Autowired
    private HttpApiUtil httpApiUtil;
    @Autowired
    private RedisCacheUtil<Object> redisCache;
    @Autowired
    private StockDataRepo stockDataRepo;


    @Scheduled(fixedRate = 1000)
    public void executeTask() {
        try{
            Calendar c = Calendar.getInstance();
            if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                logger.info("周末不开盘！");
            }else{
                if ((c.get(Calendar.HOUR_OF_DAY) >= 9 && c.get(Calendar.HOUR_OF_DAY) <= 11) || (c.get(Calendar.HOUR_OF_DAY) >= 13 && c.get(Calendar.HOUR_OF_DAY) <= 15)) {
                    String str = httpApiUtil.doGet("http://hq.sinajs.cn/list=sz399300");
                    str = str.substring(str.indexOf("\"") + 1, str.lastIndexOf("\""));
                    String [] strs = str.split(",");
                    //JSONObject json = JSON.parseObject(str);
                    //if(json.getJSONObject("1399300").getString("time") != null){
//                        StockData sd = new StockData(strs);
//                        stockDataRepo.save(sd);
//                        logger.info("_________缓存数据 ________");
                        logger.info("key:"+strs[30]+" "+strs[31]+"，value:"+str);
//                        json.put("issave", "0");//初次缓存，状态为：未保存
                        redisCache.setCacheObject(strs[30]+" "+strs[31], str);
                    //}
                } else {
                    logger.info("非开盘时间点无需记录！");
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Scheduled(fixedRate = 10000)
    public void executeData() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, -10);
        logger.info("________读取缓存数据________");
        for (int i = 10; i > 0; i--) {
            c.add(Calendar.SECOND, -1);
            Object json = redisCache.getCacheObject(CommonUtil.dateToString(c.getTime(), "yyyy-MM-dd HH:mm:ss"));
            logger.info("key:" + CommonUtil.dateToString(c.getTime(), "yyyy-MM-dd HH:mm:ss") + "，value:" + json);
            if(json != null){
                String[] jsons = json.toString().split(",");
                StockData sd = new StockData(jsons);
                stockDataRepo.save(sd);
            }

        }
        logger.info("_________结束读取缓存数据________");
    }

}
