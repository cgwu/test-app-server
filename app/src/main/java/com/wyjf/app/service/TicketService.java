package com.wyjf.app.service;

import com.wyjf.common.constant.TranType;
import com.wyjf.common.domain.*;
import com.wyjf.common.repository.DrawRepo;
import com.wyjf.common.repository.LogBalanceRepo;
import com.wyjf.common.repository.TicketRepo;
import com.wyjf.common.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/9/13.
 */
@Service
public class TicketService {
    private static final Logger log = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private SystemParamService systemParamService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private DrawRepo drawRepo;

    @Autowired
    private LogBalanceRepo logBalanceRepo;

    /**
     * 投票（涨、跌）
     *
     * @param ticket
     * @return 状态码
     * 1: 盘口不存在
     * 2: 盘口投票时间已过
     * 3: 会员不存在
     * 4: 会员余额不足
     * 5: 金额有误
     */
    @Transactional
    public Pair<Integer, String> buy(Ticket ticket) {
        if (ticket.getAmount() <= 0) {
            return Pair.of(5, "金额有误");
        }
        // 检查盘口存在
        Draw draw = drawRepo.findOne(ticket.getDid());
        if (draw == null) {
            return Pair.of(1, "盘口不存在");
        }

        // 检查盘口时间
        int beforeMins = systemParamService.getBeforeBuyMins();
        if (LocalDateTime.now().isAfter(draw.getStartDate().minusMinutes(beforeMins))) {
            return Pair.of(2, "该盘口购买时间段已过");
        }

        // 检查会员存在
        User u = userRepo.findOne(ticket.getUid());
        if (u == null) {
            return Pair.of(3, "会员不存在");
        }
        // 检查金额
        if (u.getBalance() < ticket.getRealAmount()) {
            return Pair.of(4, "会员余额不足");
        }

        // 减去会员金额
        userRepo.addBalance(ticket.getUid(), -ticket.getAmount());

        //保存票
        Ticket saved = ticketRepo.saveAndFlush(ticket);

        //累计池
        if (ticket.getDirection() == 1) {
            drawRepo.buyAmountUp(ticket.getDid(), ticket.getAmount());
        } else {
            drawRepo.buyAmountDown(ticket.getDid(), ticket.getAmount());
        }

        //保存日志 log_balance
        LogBalance log = new LogBalance();
        log.setUid(ticket.getUid());
        log.setAmount(ticket.getAmount());
        log.setType(TranType.LOG_WALLET);
        log.setTag(draw.getDrawSeq());
        log.setRefId(saved.getTid());
        log.setLogTime(ticket.getBuyTime());
        logBalanceRepo.save(log);

        return Pair.of(0, "成功");
    }
}
