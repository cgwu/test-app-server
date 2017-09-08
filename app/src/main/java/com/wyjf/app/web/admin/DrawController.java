package com.wyjf.app.web.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/9/5.
 * 盘口
 */
@Controller
@RequestMapping("/admin/draw")
public class DrawController {
    private static final Logger log = LoggerFactory.getLogger(DrawController.class);

    @RequestMapping("/list")
    public String list() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            log.info("principal instanceof UserDetails");
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        log.info("当前登陆用户:{}", username);

        return "admin/draw/list";
    }


}
