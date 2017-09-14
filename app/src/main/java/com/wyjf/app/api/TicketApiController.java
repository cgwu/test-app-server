package com.wyjf.app.api;

import com.wyjf.app.service.TicketService;
import com.wyjf.common.domain.Ticket;
import com.wyjf.common.domain.User;
import com.wyjf.common.repository.UserRepo;
import com.wyjf.common.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/ticket")
@Api(description = "买注接口")
public class TicketApiController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserRepo userRepo;

    @RequestMapping(value = {"/buy"}, method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "授权码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "drawId", value = "盘口ID", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "direction", value = "买注方向(1涨；0跌)", required = true, paramType = "query", dataType = "Int"),
            @ApiImplicitParam(name = "amount", value = "买注金额", required = true, paramType = "query", dataType = "Double")
    })
    @ApiOperation(value = "买注", notes = "买注接口,返回状态码code:\n" +
            "*  0: 成功\n" +
            "*  1: 盘口不存在\n" +
            "*  2: 盘口投票时间已过\n" +
            "*  3: 会员不存在\n" +
            "*  4: 会员余额不足\n"+
            "*  5: Token不存在或已过时",
            produces = "application/json")
    public ApiResult buy(
            @RequestParam String token,
            @RequestParam long drawId,
            @RequestParam int direction,
            @RequestParam double amount
    ) {
        User user = userRepo.findByTokenOrTime(token);
        if (user == null) {
            return ApiFactory.fail(5, "Token不存在或已过时");
        }
        Ticket ticket = new Ticket();
        ticket.setSid(CommonUtil.getSerialNO());
        ticket.setUid(user.getUid());
        ticket.setDid(drawId);
        ticket.setDirection(direction); // 方向：涨1，跌0
        ticket.setAmount(amount);
        ticket.setRealAmount(amount);
        ticket.setBuyTime(LocalDateTime.now());
        ticket.setStatus(0);

        int code = ticketService.buy(ticket);
        return ApiFactory.createResult(code, "", null);
    }

}
