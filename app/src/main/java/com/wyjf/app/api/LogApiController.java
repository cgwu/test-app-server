package com.wyjf.app.api;

import com.wyjf.common.domain.LogBalance;
import com.wyjf.common.domain.User;
import com.wyjf.common.message.ApiCode;
import com.wyjf.common.repository.LogBalanceRepo;
import com.wyjf.common.repository.UserRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 2017/8/22.
 */
@RestController
@RequestMapping(value = "/api/log")
@Api(description = "日志相关操作")
public class LogApiController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LogBalanceRepo logBalanceRepo;

    @ApiOperation(value = "交易日志", notes = "查询交易日志列表接口 \n" +
            "tag:子类型标识(1-5下注盘口类型, 6返现, 7中奖).\n" +
            "返回状态码code:\n" +
            "0: 成功,\n" +
            "8: 授权码(Token)不存在或已过时", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "授权码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "交易类型(0云投记录，1充值记录，2提款记录)", required = true, paramType = "query", dataType = "Int"),
    })
    @RequestMapping(value = {"/balance"}, method = RequestMethod.POST)
    public ApiResult balance(
            @RequestParam String token,
            @RequestParam int type
    ) {
        User user = userRepo.findByTokenOrTime(token);
        if (user == null) {
            return ApiFactory.fail(ApiCode.TOKEN_INVALID, "授权码(Token)不存在或已过时");
        }
        List<LogBalance> list = logBalanceRepo.findByUidAndTypeOrderByLogTimeDesc(user.getUid(), type);
        return ApiFactory.createResult(list);
    }
}
