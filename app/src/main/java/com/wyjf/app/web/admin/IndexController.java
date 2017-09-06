package com.wyjf.app.web.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/9/5.
 */
@Controller
@RequestMapping("/admin")
public class IndexController {
    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/index")
    public String index() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            log.info("principal instanceof UserDetails");
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        log.info("当前登陆用户:{}", username);

        return "admin/index";
    }


    @RequestMapping("/foo")
    public String foo() {
        return "admin/foo";
    }
}
