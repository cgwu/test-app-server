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

        if(date.getHour() == 9 && date.getMinute() == 30){
            String dateStr = time.format(dtf);
            return stockDataRepo.findStockOpen(dateStr);
        }

        if(date.getHour() == 11 && date.getMinute() == 30){
            time = date.plusMinutes(-1);
            String dateStr = time.format(dtf);
            return stockDataRepo.findStockData(dateStr);
        }

        String dateStr = time.format(dtf);
        return stockDataRepo.findStockData(dateStr);
    }

    /**
     *
     * @param date  今天
     * @return
     */
    public Double getDataByYesterday(LocalDateTime date){
        LocalDateTime time = date.plusMinutes(0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStr = time.format(dtf);
        return stockDataRepo.findStockYestClose(dateStr);
    }
}
