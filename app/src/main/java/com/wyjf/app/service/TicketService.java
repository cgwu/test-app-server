package com.wyjf.app.service;

import com.wyjf.common.constant.TranType;
import com.wyjf.common.domain.*;
import com.wyjf.common.repository.DrawRepo;
import com.wyjf.common.repository.LogBalanceRepo;
import com.wyjf.common.repository.TicketRepo;
import com.wyjf.common.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/13.
 */
@Service
public class TicketService {
    private static final Logger log = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SystemParamService systemParamService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private DrawRepo drawRepo;

    @Autowired
    private LogBalanceRepo logBalanceRepo;

    /**
     * 投票（涨、跌）
     *
     * @param ticket
     * @return 状态码
     * 1: 盘口不存在
     * 2: 盘口投票时间已过
     * 3: 会员不存在
     * 4: 会员余额不足
     * 5: 金额有误
     */
    @Transactional
    public Pair<Integer, String> buy(Ticket ticket) {
        if (ticket.getAmount() <= 0) {
            return Pair.of(5, "金额有误");
        }
        // 检查盘口存在
        Draw draw = drawRepo.findOne(ticket.getDid());
        if (draw == null) {
            return Pair.of(1, "盘口不存在");
        }

        // 检查盘口时间
        int beforeMins = systemParamService.getBeforeBuyMins();
        if (LocalDateTime.now().isAfter(draw.getStartDate().minusMinutes(beforeMins))) {
            return Pair.of(2, "该盘口购买时间段已过");
        }

        // 检查会员存在
        User u = userRepo.findOne(ticket.getUid());
        if (u == null) {
            return Pair.of(3, "会员不存在");
        }
        // 检查金额
        if (u.getBalance() < ticket.getRealAmount()) {
            return Pair.of(4, "会员余额不足");
        }

        // 减去会员金额
        userRepo.addBalance(ticket.getUid(), -ticket.getAmount());

        //保存票
        Ticket saved = ticketRepo.saveAndFlush(ticket);

        //累计池
        if (ticket.getDirection() == 1) {
            drawRepo.buyAmountUp(ticket.getDid(), ticket.getAmount());
        } else {
            drawRepo.buyAmountDown(ticket.getDid(), ticket.getAmount());
        }

        //保存日志 log_balance
        LogBalance log = new LogBalance();
        log.setUid(ticket.getUid());
        log.setAmount(-ticket.getAmount()); //下注金额日志记为负.
        log.setType(TranType.LOG_WALLET);
        log.setTag(draw.getDrawSeq());
        log.setRefId(saved.getTid());
        log.setLogTime(ticket.getBuyTime());
        logBalanceRepo.save(log);

        return Pair.of(0, "成功");
    }


    private static final String SQL_DETAIL = "select t.`tid`, t.`sid`, t.`uid`, t.`did`, t.`direction`, t.`amount`, DATE_FORMAT(t.`buy_time`,'%Y-%m-%d %H:%i:%s') as buy_time, t.`status`, \n" +
            "\td.draw_day, d.draw_seq, concat(DATE_FORMAT(d.draw_day,'%Y%m%d-'),d.draw_seq) as draw_no , dr.result as result_direction,  \n" +
            "\tcase when r.win_amount is null then 0 else 1 end as is_win, ifnull(win_amount, 0) as win_amount \n" +
            "from ticket t left join ticket_result_win r on t.tid = r.tid \n" +
            "left join draw d on t.did = d.did \n" +
            "left join draw_result dr on t.did = dr.did \n" +
            "where t.tid = ?";

    public Map<String, Object> detail(long tid) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList(SQL_DETAIL, tid);
        if (list.size() > 0) return list.get(0);
        else return null;
    }

    public Map<String, Object> detail(long tid, long uid) {
        Map<String, Object> map = detail(tid);
        if (map != null) {
            if (uid != (Long) map.get("uid")) return null;
        }
        return map;
    }
}
