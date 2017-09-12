package com.wyjf.app.service;

import com.querydsl.core.Tuple;
import com.wyjf.common.domain.Draw;
import com.wyjf.common.repository.DrawRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/12.
 */
@Service
public class DrawService {
    private static final Logger log = LoggerFactory.getLogger(DrawService.class);

    @Autowired
    private DrawRepo repo;

    @Transactional
    public boolean addDrawByDate(LocalDate day) {
        List<Draw> list = repo.findByDrawDay(day);
        if (list.size() > 0) {
            log.warn("{}该日期已存在盘口，添加失败!", day);
            return false;
        }

        List<LocalDateTime[]> dates = new ArrayList<LocalDateTime[]>();
        dates.add(new LocalDateTime[]{day.atTime(9, 30), day.atTime(10, 30)});
        dates.add(new LocalDateTime[]{day.atTime(10, 30), day.atTime(11, 30)});
        dates.add(new LocalDateTime[]{day.atTime(13, 0), day.atTime(14, 0)});
        dates.add(new LocalDateTime[]{day.atTime(14, 0), day.atTime(15, 00)});
        dates.add(new LocalDateTime[]{day.atTime(9, 30), day.atTime(15, 00)}); //全天

        for (int i = 1; i <= 5; i++) {
            LocalDateTime[] dt = dates.get(i - 1);
            Draw draw = new Draw();
            draw.setDrawDay(day);
            draw.setDrawSeq(i);
            draw.setStartDate(dt[0]);
            draw.setEndDate(dt[1]);
            draw.setAmountUp(0);
            draw.setAmountDown(0);
            draw.setStatus(0);
            repo.save(draw);
        }
        return true;
    }

}
