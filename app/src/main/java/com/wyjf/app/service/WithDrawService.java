package com.wyjf.app.service;

import com.wyjf.common.constant.TranType;
import com.wyjf.common.constant.WithDrawStatus;
import com.wyjf.common.domain.LogBalance;
import com.wyjf.common.domain.User;
import com.wyjf.common.domain.WithDraw;
import com.wyjf.common.repository.LogBalanceRepo;
import com.wyjf.common.repository.UserRepo;
import com.wyjf.common.repository.WithDrawRepo;
import com.wyjf.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/25 0025.
 */
@Service
public class WithDrawService {
    private static final Logger logger = Logger.getLogger(WithDrawService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private WithDrawRepo withDrawRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private LogBalanceRepo logBalanceRepo;
    public Map<String, Object> findWithDrawList(PageRequest pageRequest, HttpServletRequest request){
        int pageSize = pageRequest.getPageSize();
        int pageNumber = pageRequest.getPageNumber();
        logger.info("第几页："+pageNumber);
        logger.info("一页几条："+pageSize);
        StringBuffer sb = new StringBuffer(128);
        sb.append("SELECT " +
                "count(0) `count` " +
                "FROM " +
                "withdraw wd " +
                "LEFT JOIN `user` u ON u.uid = wd.uid " +
                "LEFT JOIN bank_card bc ON bc.id = wd.bcid " +
                "WHERE " +
                "1 = 1 ");
        if(!CommonUtil.checkEmpty(request.getParameter("phone"))){
            sb.append(" AND u.phone = "+request.getParameter("phone"));
        }
        List<Map<String, Object>> countList = jdbcTemplate.queryForList(sb.toString());
        StringBuffer sbCount = new StringBuffer(128);
        sbCount.append("SELECT " +
                "wd.id, wd.money, wd.create_time, wd.handing_time, wd.`status`, wd.remark, " +
                "u.uid uid, u.nickname, u.balance, u.phone, bc.id bcid, bc.card_number, bc.bank, bc.open_bank, bc.real_name\n" +
                "FROM " +
                "withdraw wd " +
                "LEFT JOIN `user` u ON u.uid = wd.uid " +
                "LEFT JOIN bank_card bc ON bc.id = wd.bcid " +
                "WHERE " +
                "1 = 1 ");
        if(!CommonUtil.checkEmpty(request.getParameter("phone"))){
            sbCount.append(" AND u.phone = "+request.getParameter("phone"));
        }
        sbCount.append(" LIMIT "+pageNumber*pageSize+", "+pageSize+"");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sbCount.toString());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", countList.get(0).get("count"));
        map.put("content", list);
        logger.info(list);
        return map;
    }

    @Transactional
    public void userWithRrawCommit(User user, Integer cardId, Double money){
        WithDraw withDraw = new WithDraw();
        withDraw.setStatus(WithDrawStatus.CHECKING);
        withDraw.setCreateTime(CommonUtil.getTokenDateTime(0));
        withDraw.setUid(user.getUid());
        withDraw.setBcid(cardId.longValue());
        withDraw.setMoney(money);
        withDrawRepo.save(withDraw);
        userRepo.addBalance(user.getUid(), -money);
        //保存日志 log_balance
        LogBalance log = new LogBalance();
        log.setUid(user.getUid());
        log.setAmount(money);
        log.setType(TranType.WITHDRAW);
        log.setLogTime(withDraw.getCreateTime());
        log.setRefId(withDraw.getId());
        logBalanceRepo.save(log);
    }

    @Transactional
    public void updateWithDraw(Integer id, Integer type){
        WithDraw withDraw = withDrawRepo.findOne(id.longValue());
        if(withDraw != null && withDraw.getStatus() == WithDrawStatus.CHECKING && type == 1){
            withDraw.setStatus(WithDrawStatus.CHECKSUCCESS);
            withDraw.setHandingTime(CommonUtil.getTokenDateTime(0));
            withDrawRepo.save(withDraw);
        }else{
            if(withDraw.getStatus() == WithDrawStatus.CHECKING){
                withDraw.setStatus(WithDrawStatus.CHECKFAIL);
                withDraw.setHandingTime(CommonUtil.getTokenDateTime(0));
                withDrawRepo.save(withDraw);
                userRepo.addBalance(withDraw.getUid(), +withDraw.getMoney());
                //保存日志 log_balance
                LogBalance log = new LogBalance();
                log.setUid(withDraw.getUid());
                log.setAmount(+withDraw.getMoney());
                log.setType(TranType.WITHDRAW);
                log.setLogTime(withDraw.getCreateTime());
                log.setRefId(withDraw.getId());
                logBalanceRepo.save(log);
            }
        }

    }
}
