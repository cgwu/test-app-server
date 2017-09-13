package com.wyjf.app.service;

import com.wyjf.common.domain.Ticket;
import com.wyjf.common.repository.DrawRepo;
import com.wyjf.common.repository.TicketRepo;
import com.wyjf.common.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/9/13.
 */
@Service
public class TicketService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TicketRepo ticketRepo;
    @Autowired
    private DrawRepo drawRepo;

    @Transactional
    public int buy(Ticket ticket){
        // 检查盘口存在
        // 检查时间
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
