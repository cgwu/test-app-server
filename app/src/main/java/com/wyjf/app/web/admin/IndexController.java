package com.wyjf.app.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/9/5.
 */
@Controller
@RequestMapping("/admin")
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "admin/index";
    }

}
