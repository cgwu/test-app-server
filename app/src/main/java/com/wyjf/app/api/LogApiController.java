package com.wyjf.app.api;

import com.querydsl.core.types.Predicate;
import com.wyjf.common.constant.TranType;
import com.wyjf.common.domain.LogBalance;
import com.wyjf.common.domain.QLogBalance;
import com.wyjf.common.domain.User;
import com.wyjf.common.message.ApiCode;
import com.wyjf.common.message.LogBalanceEx;
import com.wyjf.common.repository.LogBalanceRepo;
import com.wyjf.common.repository.UserRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private static final Logger log = LoggerFactory.getLogger(LogApiController.class);

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
            @ApiImplicitParam(name = "offset", value = "偏移下标,从0开始", required = false, paramType = "query", dataType = "Int"),
            @ApiImplicitParam(name = "length", value = "最大条数", required = false, paramType = "query", dataType = "Int")
    })
    @RequestMapping(value = {"/balance"}, method = RequestMethod.POST)
    public ApiResult balance(
            @RequestParam String token,
            @RequestParam int type,
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer length
    ) {
        User user = userRepo.findByTokenOrTime(token);
        if (user == null) {
            return ApiFactory.fail(ApiCode.TOKEN_INVALID, "授权码(Token)不存在或已过时");
        }
        int ioffset = 0;
        if (offset != null) ioffset = offset.intValue();
        int ilength = 10;
        if (length != null && 0 != length.intValue()) ilength = length.intValue();
        log.info("uid:{},type:{},offset:{},length:{}", user.getUid(), type, ioffset, ilength);

        if (type == TranType.LOG_WALLET) {
            List<LogBalanceEx> list = logBalanceRepo.findLogBalanceEx(user.getUid(), type, ioffset, ilength);
            return ApiFactory.createResult(list);
        } else {
            log.info("这里");
            Predicate predicate = QLogBalance.logBalance.uid.eq(user.getUid())
                    .and(QLogBalance.logBalance.type.eq(type));
            PageRequest preq = new PageRequest(ioffset / ilength, ilength, Sort.Direction.DESC, "lid");
            Page<LogBalance> list = logBalanceRepo.findAll(predicate, preq);
            return ApiFactory.createResult(list.getContent());
        }
    }
}
