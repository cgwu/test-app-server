package com.wyjf.common.repository;

import com.wyjf.common.domain.StockData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/9/1 0001.
 */
public interface StockDataRepo extends JpaRepository<StockData, Long> {
}
