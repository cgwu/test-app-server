package com.wyjf.app.service;

import com.wyjf.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
}
