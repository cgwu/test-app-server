package com.wyjf.common.repository;

import com.wyjf.common.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by Administrator on 2017/9/13 0013.
 */
public interface OrderRepo extends JpaRepository<Order, Long>, QueryDslPredicateExecutor<Order> {
    public Order findByOrderNumber(String orderNumber);
}
