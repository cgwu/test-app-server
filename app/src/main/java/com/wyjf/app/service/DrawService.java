package com.wyjf.app.service;

import com.querydsl.core.Tuple;
import com.wyjf.common.domain.Draw;
import com.wyjf.common.repository.DrawRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private JdbcTemplate jdbcTemplate;

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


    private static final String SQL_DETAIL = "select d.did, d.draw_day, d.draw_seq,  d.amount_up, d.amount_down, d.status,\n" +
            " DATE_FORMAT(d.start_date,'%Y-%m-%d %H:%i:%s') as start_date,\n" +
            " DATE_FORMAT(d.end_date,'%Y-%m-%d %H:%i:%s') as end_date,\n" +
            " ifnull(r.start_price,0) as start_price, ifnull(r.end_price,0) as end_price, ifnull(r.result, -1) as result_direction\n" +
            " from draw d left join draw_result r on d.did = r.did" +
            " where d.did = ?";

    public Map<String, Object> detail(long tid) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList(SQL_DETAIL, tid);
        if (list.size() > 0) return list.get(0);
        else return null;
    }

}
