package com.wyjf.common.repository;

import com.wyjf.common.domain.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/9/20 0020.
 */
public interface BankCardRepo extends JpaRepository<BankCard, Long>{

    public List<BankCard> findByUidAndIsDel(Long uid, Integer isDel);

    @Query(value = "SELECT " +
            "bc " +
            "FROM " +
            "WithDraw wd  " +
            "LEFT JOIN BankCard bc ON bc.id = wd.bcid " +
            "WHERE wd.uid = :uId " +
            "ORDER BY wd.id DESC ")
    public List<BankCard> findByUidAsOne(@Param("uId") Long uId);

    public List<BankCard> findByCardNumberAndIsDel(String cardNumber, Integer isDel);
}