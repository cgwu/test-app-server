package com.wyjf.app.api;

import com.wyjf.app.service.OrderService;
import com.wyjf.common.domain.User;
import com.wyjf.common.repository.OrderRepo;
import com.wyjf.common.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/9/13 0013.
 */
public class BaseController {
    @Autowired
    public UserRepo userRepo;
    @Autowired
    public OrderRepo orderRepo;
    @Autowired
    public OrderService orderService;


    /**
     * 验证token，token有效返回true
     * @param token
     * @return
     */
    public boolean checkToken(String token) {
        User user = userRepo.findByTokenOrTime(token);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }
}
