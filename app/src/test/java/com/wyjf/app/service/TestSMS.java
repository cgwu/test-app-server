package com.wyjf.app.service;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Administrator on 2017/9/20.
 */
public class TestSMS {
    private static final Logger log = LoggerFactory.getLogger(TestSMS.class);

    @Test
    public void testSend() {
        //初始化client,apikey作为所有请求的默认值(可以为空)
        YunpianClient clnt = new YunpianClient("a0fced221849f75ce444c85ed5203d90").init();

        //修改账户信息API
        Map<String, String> param = clnt.newParam(2);
        param.put(YunpianClient.MOBILE, "18818696521");
        param.put(YunpianClient.TEXT, "【爱云投】您的验证码是5678");
        Result<SmsSingleSend> r = clnt.sms().single_send(param);
        //获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),其他说明:r.getDetail(),调用异常:r.getThrowable()
        log.info("code:{}, msg:{}, data:{}, detail:{}", r.getCode(), r.getMsg(), r.getData(), r.getDetail());

        //账户:clnt.user().* 签名:clnt.sign().* 模版:clnt.tpl().* 短信:clnt.sms().* 语音:clnt.voice().* 流量:clnt.flow().* 隐私通话:clnt.call().*

        //最后释放client
        clnt.close();
    }

}
