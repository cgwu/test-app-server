package com.wyjf.app.service;

import com.wyjf.common.repository.StockDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Administrator on 2017/9/18 0018.
 */
@Service
public class StockDataService {
    @Autowired
    private StockDataRepo stockDataRepo;

    public Double getData(LocalDateTime date){
        LocalDateTime time = date.plusMinutes(0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String dateStr = time.format(dtf);
        if(date.getHour() == 9){
            return stockDataRepo.findStockOpen(dateStr);
        }
        return stockDataRepo.findStockData(dateStr);
    }
}
