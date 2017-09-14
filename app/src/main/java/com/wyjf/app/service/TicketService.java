package com.wyjf.app.service;

import com.wyjf.common.domain.Draw;
import com.wyjf.common.domain.SystemParam;
import com.wyjf.common.domain.Ticket;
import com.wyjf.common.repository.DrawRepo;
import com.wyjf.common.repository.TicketRepo;
import com.wyjf.common.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 投票（涨、跌）
     * @param ticket
     * @return 状态码
     *  1: 盘口不存在
     */
    @Transactional
    public int buy(Ticket ticket){
        // 检查盘口存在
        Draw draw = drawRepo.findOne(ticket.getDid());
        if(draw == null){
            return 1;
        }

        // 检查盘口时间
        SystemParam pm = systemParamService.findByKey("INT_BeforeBuyMins");
        int beforeMins = 0;
        try {
            beforeMins = Integer.parseInt(pm.getDataVal());
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
        }
//        LocalDateTime.now().isAfter( draw.getStartDate().minusMinutes(beforeMins))

        // 检查会员存在
        // 检查金额

        // 减去会员金额
        userRepo.addBalance(ticket.getUid(), -ticket.getAmount());

        //保存票
        ticketRepo.save(ticket);
        //累计池
        if(ticket.getDirection() == 1){
            drawRepo.buyAmountUp(ticket.getDid(), ticket.getAmount());
        }
        else{
            drawRepo.buyAmountDown(ticket.getDid(), ticket.getAmount());
        }
        //保存日志 log_balance

        return 0;
    }
}
