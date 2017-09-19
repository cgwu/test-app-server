package com.wyjf.app.api;

import com.wyjf.common.domain.Order;
import com.wyjf.common.domain.User;
import com.wyjf.common.message.UserResult;
import com.wyjf.common.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/9/13 0013.
 */
@RestController
@RequestMapping(value = "/api/order")
@Api(description = "充值接口")
public class OrderController extends BaseController{


    @ApiOperation(value = "提交充值订单（需传token）", notes = "", produces = "application/json")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "query", dataType = "Int"),
            @ApiImplicitParam(name = "money", value = "充值金额", required = true, paramType = "query", dataType = "Double"),
            @ApiImplicitParam(name = "paytype", value = "支付类型（0微信，1支付宝）", required = true, paramType = "query", dataType = "Int"),
            @ApiImplicitParam(name = "remark", value = "备注", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "用户Token", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = {"/commitOrder"}, method = RequestMethod.POST)
    public ApiResult commitOrder(@RequestParam Double money, @RequestParam Integer paytype, @RequestParam Integer userId, @RequestParam(required = false) String remark, @RequestParam String token){
        if (!checkToken(token)) {
            return ApiFactory.createResult(8, "请重新登陆", null);
        }
        if(money != null && paytype != null && money > 0.0){
            User user = userRepo.findOne(userId.longValue());
            if(user == null){
                return ApiFactory.createResult(1, "用户不存在", null);
            }
            Order order = new Order(money, paytype, user, remark);
            orderRepo.save(order);
            if(order.getOid() != null){
                return ApiFactory.createResult(0, "订单提交成功，请支付款项", order);
            }else{
                return ApiFactory.createResult(1, "订单提交失败，请联系管理员", null);
            }
        }else{
            return ApiFactory.createResult(1, "参数错误", null);
        }
    }


    @ApiOperation(value = "充值测试（需传token）", notes = "", produces = "application/json")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "query", dataType = "Int"),
            @ApiImplicitParam(name = "money", value = "充值金额", required = true, paramType = "query", dataType = "Double"),
            @ApiImplicitParam(name = "token", value = "用户Token", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = {"/testcommitOrder"}, method = RequestMethod.POST)
    public ApiResult testcommitOrder(@RequestParam Double money, @RequestParam String token, @RequestParam Integer userId){
        if (!checkToken(token)) {
            return ApiFactory.createResult(8, "请重新登陆", null);
        }
        if(money != null && money > 0.0){
            Order order = orderRepo.findOne(1L);
            order.setOrderMoeny(money);
            order.setUid(userId.longValue());
            orderService.userWithdraw(order);
            User user = userRepo.findOne(userId.longValue());
            UserResult userResult = new UserResult(user, null);
            return ApiFactory.createResult(0, "充值成功", userResult);
        }else{
            return ApiFactory.createResult(1, "参数错误", null);
        }
    }

}
