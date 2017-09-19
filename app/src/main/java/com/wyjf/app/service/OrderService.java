package com.wyjf.app.service;

import com.wyjf.common.constant.OrderStatus;
import com.wyjf.common.constant.TranType;
import com.wyjf.common.domain.LogBalance;
import com.wyjf.common.domain.Order;
import com.wyjf.common.domain.User;
import com.wyjf.common.repository.LogBalanceRepo;
import com.wyjf.common.repository.OrderRepo;
import com.wyjf.common.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/9/19 0019.
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private LogBalanceRepo logBalanceRepo;


    public void userWithdraw(Order order){
        //修改用户余额
        User u = userRepo.findOne(order.getUid());
        u.setBalance(u.getBalance() + order.getOrderMoeny());
        userRepo.save(u);

        //修改订单状态
        order.setPayTime(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.PREPAID);
        orderRepo.save(order);

        //保存日志 log_balance
        LogBalance log = new LogBalance();
        log.setUid(u.getUid());
        log.setAmount(order.getOrderMoeny());
        log.setType(TranType.DEPOSIT);
        log.setLogTime(order.getPayTime());
        logBalanceRepo.save(log);
    }
}
