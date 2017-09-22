package com.wyjf.app.service;


import com.wyjf.common.domain.Draw;
import com.wyjf.common.domain.DrawResult;
import com.wyjf.common.repository.DrawRepo;
import com.wyjf.common.repository.DrawResultRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/9/12.
 */
@Service
public class DrawResultService {
    private static final Logger log = LoggerFactory.getLogger(DrawResultService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SystemParamService systemParamService;
    @Autowired
    private StockDataService stockDataService;

    @Autowired
    private DrawRepo drawRepo;
    @Autowired
    private DrawResultRepo repo;


    @Transactional
    public Pair<Integer, String> process(long drawId) {
        Draw draw = drawRepo.findOne(drawId);
        if (draw == null) {
            return Pair.of(1, "该盘口不存在");
        }
        if(draw.getStatus() == 1){
            return Pair.of(4, "该盘口已结账,不能重复结账");
        }
        double percent = systemParamService.getPlatformProfitPercent();
        if (percent < 0) {
            return Pair.of(2, "平台收益百分比设置错误");
        }
        Double startPrice = stockDataService.getData(draw.getStartDate());
        Double endPrice = stockDataService.getData(draw.getEndDate());
        if (startPrice == null || endPrice == null) {
            return Pair.of(3, "该盘口开始指数或结束指数为空");
        }
        int resultDirection = 0;    //1涨,0跌
        if (endPrice > startPrice) resultDirection = 1;

        double win = 0, divisor = 0;
        if (resultDirection == 1) {
            win = draw.getAmountDown(); //投涨，跌输
            divisor = draw.getAmountUp();
        } else {
            win = draw.getAmountUp();   //投跌，涨输
            divisor = draw.getAmountDown();
        }

        //　开始处理
        draw.setStatus(1);  //已结账
        drawRepo.save(draw);

        LocalDateTime now = LocalDateTime.now();
        DrawResult result = new DrawResult();
        result.setDid(draw.getDid());
        result.setStartPrice(startPrice);
        result.setEndPrice(endPrice);
        result.setResult(resultDirection);
        result.setPlatformPercent(percent);
        result.setPlatformAmount(win * percent / 100);
        result.setPrizeAmount(win - result.getPlatformAmount());
        result.setProcessTime(now);
        repo.save(result);

        int affected = jdbcTemplate.update("insert into ticket_result_win (tid, did, win_percent, win_amount) \n" +
                        "select tid, did,amount / ?, amount / ? * ? from ticket where did = ? and direction = ?"
                , divisor, divisor, result.getPrizeAmount(), drawId, resultDirection);
        log.info("insert ticket_result_win affected:{}", affected);

        affected = jdbcTemplate.update("insert into log_balance (uid, amount, type, tag, ref_id, log_time)\n" +
                        " select t.uid, t.amount, 0, 6, w.tid, ? from ticket_result_win w left join ticket t on w.tid = t.tid\n" +
                        " where w.did = ?" , now, drawId);
        log.info("返现 affected:{}", affected);

        affected = jdbcTemplate.update("insert into log_balance (uid, amount, type, tag, ref_id, log_time) \n" +
                "select t.uid, w.win_amount, 0, 7, w.tid, ? from ticket_result_win w left join ticket t on w.tid = t.tid\n" +
                "where w.did = ?" , now, drawId);
        log.info("中奖 affected:{}", affected);

        affected = jdbcTemplate.update("update `user` as U \n" +
                "inner join \n" +
                "(" +
                " select uid as update_uid ,sum(amount) as total_amount from(\n" +
                "  select t.uid, t.amount from ticket_result_win w left join ticket t on w.tid = t.tid\n" +
                "  where w.did = ?\n" +
                "  union all\n" +
                "  select t.uid, w.win_amount as amount from ticket_result_win w left join ticket t on w.tid = t.tid\n" +
                "  where w.did = ?) t group by uid \n" +
                ") as S on S.update_uid = U.uid\n" +
                "set U.balance = U.balance + S.total_amount" , drawId, drawId);
        log.info("增加余额 affected:{}", affected);

        affected = jdbcTemplate.update("update ticket set status = 1 where did = ?" , drawId);
        log.info("更改票状态 affected:{}", affected);

        return Pair.of(0, "结账成功");
    }
}
