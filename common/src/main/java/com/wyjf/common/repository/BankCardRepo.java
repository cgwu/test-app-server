package com.wyjf.common.repository;

import com.wyjf.common.domain.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/9/20 0020.
 */
public interface BankCardRepo extends JpaRepository<BankCard, Long>{

    public List<BankCard> findByUid(Long uid);
}