package com.wyjf.app.service;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/21.
 */
@Service
public class SmsService {
    private static final Logger log = LoggerFactory.getLogger(SmsService.class);

    private YunpianClient clnt;

    public Pair<Integer, String> send(String phone, String msg) {
        Map<String, String> param = clnt.newParam(2);
//        param.put(YunpianClient.MOBILE, "188xxx");
//        param.put(YunpianClient.TEXT, "【云投网】您的验证码是5678");
        param.put(YunpianClient.MOBILE, phone);
        param.put(YunpianClient.TEXT, msg);
        Result<SmsSingleSend> r = clnt.sms().single_send(param);
        if (r.getCode() != 0) {
            log.error("发送短信失败,code:{}, msg:{}, data:{}, detail:{}", r.getCode(), r.getMsg(), r.getData(), r.getDetail());
        }
        return Pair.of(r.getCode(), r.getMsg());
    }

    @PostConstruct
    public void init() {
        clnt = new YunpianClient("a0fced221849f75ce444c85ed5203d90").init();
        log.info("初始短信发送器");
    }

    @PreDestroy
    public void destroy() {
        clnt.close();
        log.info("销毁短信发送器");
    }

}
