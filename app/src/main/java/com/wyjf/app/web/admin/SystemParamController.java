package com.wyjf.app.web.admin;

import com.wyjf.common.domain.User;
import com.wyjf.common.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
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
    private UserRepo userRepo;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listGet() {
        return "admin/systemparam/list";
    }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> listPost() {
        List<User> users = userRepo.findAll();
        HashMap map = new HashMap();
        map.put("data", users);
        map.put("draw", 1);
        map.put("recordsTotal", users.size());
        map.put("recordsFiltered", users.size());
        return map;
    }

}
