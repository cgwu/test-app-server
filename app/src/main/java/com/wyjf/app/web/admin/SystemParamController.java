package com.wyjf.app.web.admin;

import com.querydsl.core.types.Predicate;
import com.wyjf.common.domain.QSystemParam;
import com.wyjf.common.domain.SystemParam;
import com.wyjf.common.domain.User;
import com.wyjf.common.message.AjaxPageRequest;
import com.wyjf.common.message.SysParamQueryRequest;
import com.wyjf.common.repository.SystemParamRepo;
import com.wyjf.common.repository.UserRepo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/5.
 * 系统参数
 */
@Controller
@RequestMapping("/admin/systemparam")
public class SystemParamController {
    private static final Logger log = LoggerFactory.getLogger(SystemParamController.class);

    @Autowired
    private HttpServletRequest http;

    @Autowired
    private SystemParamRepo repo;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "admin/systemparam/list";
    }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> listQuery(@ModelAttribute SysParamQueryRequest req) {
        PageRequest pReq = req.getPage(http);
        Predicate predicate = null;
        if (req.getDataKey() != null) {
            predicate = QSystemParam.systemParam.dataKey.likeIgnoreCase("%" + req.getDataKey() + "%")
                    .or(QSystemParam.systemParam.comment.likeIgnoreCase("%" + req.getComment() + "%"));
        }
        log.info("{},{},{}", req.getDataKey(), req.getComment(), predicate == null);
        Page<SystemParam> page = repo.findAll(predicate, pReq);     // 可以为空，代表无where条件
        HashMap map = new HashMap();
        map.put("data", page.getContent());
        map.put("draw", req.getDraw());
        map.put("recordsTotal", page.getTotalElements());
        map.put("recordsFiltered", page.getTotalElements());
        return map;
    }

}
