package com.wyjf.app.service;

import com.wyjf.app.share.Debug;
import com.wyjf.app.share.Release;
import com.wyjf.app.share.SmsSender;
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
@Debug
public class SmsDebugService implements SmsSender {
    private static final Logger log = LoggerFactory.getLogger(SmsDebugService.class);


    public Pair<Integer, String> send(String phone, String msg) {
        log.info("phone:{}, msg:{}", phone, msg);
        return Pair.of(0, "发送成功");
    }

    @PostConstruct
    public void init() {
        log.info("初始短信发送器{profile = debug}");
    }

    @PreDestroy
    public void destroy() {
        log.info("销毁短信发送器{profile = debug}");
    }

}
