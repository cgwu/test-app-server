package com.wyjf.app.api;

import com.wyjf.app.service.SmsService;
import com.wyjf.common.domain.LogBalance;
import com.wyjf.common.domain.User;
import com.wyjf.common.message.ApiCode;
import com.wyjf.common.repository.LogBalanceRepo;
import com.wyjf.common.repository.UserRepo;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 2017/8/22.
 */
@RestController
@RequestMapping(value = "/api/smg")
@Api(description = "短信相关操作")
public class SmsApiController {
    private static final Logger log = LoggerFactory.getLogger(SmsApiController.class);

    @Autowired
    private SmsService smsService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LogBalanceRepo logBalanceRepo;

    @ApiOperation(value = "发送短信", notes = "发送短信(该接口仅用于测试,发送内容需要匹配模板才能发送成功)", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "msg", value = "信息内容", required = true, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = {"/send"}, method = RequestMethod.POST)
    public ApiResult balance(
            @RequestParam String phone,
            @RequestParam String msg
    ) {
        /*
        YunpianClient clnt = new YunpianClient("a0fced221849f75ce444c85ed5203d90").init();

        //修改账户信息API
        Map<String, String> param = clnt.newParam(2);
        param.put(YunpianClient.MOBILE, "188xxx");
        param.put(YunpianClient.TEXT, "【云投网】您的验证码是5678");
        Result<SmsSingleSend> r = clnt.sms().single_send(param);
        //获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),其他说明:r.getDetail(),调用异常:r.getThrowable()
        log.info("code:{}, msg:{}, data:{}, detail:{}",r.getCode(), r.getMsg(), r.getData(), r.getDetail());

        //账户:clnt.user().* 签名:clnt.sign().* 模版:clnt.tpl().* 短信:clnt.sms().* 语音:clnt.voice().* 流量:clnt.flow().* 隐私通话:clnt.call().*

        //最后释放client
        clnt.close();
        return ApiFactory.createResult(r);
        */
        Pair<Integer, String> p = smsService.send(phone, msg);
        return ApiFactory.createResult(p.getFirst(), p.getSecond(), "");
    }
}
