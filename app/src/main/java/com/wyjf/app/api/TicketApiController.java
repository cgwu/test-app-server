package com.wyjf.app.api;

import com.querydsl.core.types.Predicate;
import com.wyjf.app.service.TicketService;
import com.wyjf.common.domain.QTicket;
import com.wyjf.common.domain.Ticket;
import com.wyjf.common.domain.User;
import com.wyjf.common.message.ApiCode;
import com.wyjf.common.message.BasicTicket;
import com.wyjf.common.message.MyTicket;
import com.wyjf.common.repository.TicketRepo;
import com.wyjf.common.repository.UserRepo;
import com.wyjf.common.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/ticket")
@Api(description = "买注接口")
public class TicketApiController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TicketRepo ticketRepo;

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
            "*  2: 该盘口购买时间段已过\n" +
            "*  3: 会员不存在\n" +
            "*  4: 会员余额不足\n" +
            "*  5: 金额有误\n" +
            "*  8: 授权码(Token)不存在或已过时",
            produces = "application/json")
    public ApiResult buy(
            @RequestParam String token,
            @RequestParam long drawId,
            @RequestParam int direction,
            @RequestParam double amount
    ) {
        User user = userRepo.findByTokenOrTime(token);
        if (user == null) {
            return ApiFactory.fail(ApiCode.TOKEN_INVALID, "授权码(Token)不存在或已过时");
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

        Pair<Integer, String> result = ticketService.buy(ticket);
        return ApiFactory.createResult(result.getFirst(), result.getSecond(), result.getFirst() == 0 ? userRepo.findUserBalance(user.getUid()):null);
    }


    @RequestMapping(value = {"/queryByDraw"}, method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "drawId", value = "盘口ID", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "offset", value = "偏移下标,从0开始", required = true, paramType = "query", dataType = "Int"),
            @ApiImplicitParam(name = "length", value = "最大条数", required = true, paramType = "query", dataType = "Int")
    })
    @ApiOperation(value = "盘口ticket列表", notes = "查询某盘口ticket列表接口",
            produces = "application/json")
    public ApiResult queryByDraw(
            @RequestParam long drawId,
            @RequestParam int offset,
            @RequestParam int length
    ) {
        List<BasicTicket> tickets = ticketRepo.findBaseTicket(drawId, offset, length);
        return ApiFactory.createResult(tickets);
    }


    @RequestMapping(value = {"/queryMime"}, method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "授权码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "drawId", value = "盘口ID", required = true, paramType = "query", dataType = "Long"),
    })
    @ApiOperation(value = "我的ticket列表", notes = "查询我的ticket信息列表接口,返回状态码code:\n" +
            "*  0: 成功\n" +
            "*  8: 授权码(Token)不存在或已过时",
            produces = "application/json")
    public ApiResult queryMime(
            @RequestParam String token,
            @RequestParam long drawId
    ) {
        User user = userRepo.findByTokenOrTime(token);
        if (user == null) {
            return ApiFactory.fail(ApiCode.TOKEN_INVALID, "授权码(Token)不存在或已过时");
        }

        List<MyTicket> myTickets = ticketRepo.findMyTicket(drawId, user.getUid());
        return ApiFactory.createResult(myTickets);

//        Predicate predicate = QTicket.ticket.did.eq(drawId).and(QTicket.ticket.uid.eq(user.getUid()));
//        ticketRepo.findAll(predicate, new Sort(Sort.Direction.DESC, "tid"));
    }


    @RequestMapping(value = {"/detail"}, method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "授权码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "tid", value = "票ID", required = true, paramType = "query", dataType = "Long"),
    })
    @ApiOperation(value = "查看票详细", notes = "查询我的某张票详细信息接口(status:0未结账;1已结账),返回状态码code:\n" +
            "*  0: 成功\n" +
            "*  8: 授权码(Token)不存在或已过时",
            produces = "application/json")
    public ApiResult detail(
            @RequestParam String token,
            @RequestParam long tid
    ) {
        User user = userRepo.findByTokenOrTime(token);
        if (user == null) {
            return ApiFactory.fail(ApiCode.TOKEN_INVALID, "授权码(Token)不存在或已过时");
        }
        Map<String, Object> map = ticketService.detail(tid, user.getUid());
        return ApiFactory.createResult(map);
    }

}
