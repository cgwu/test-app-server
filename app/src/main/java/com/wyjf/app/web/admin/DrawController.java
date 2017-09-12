package com.wyjf.app.web.admin;

import com.wyjf.common.domain.Draw;
import com.wyjf.common.domain.User;
import com.wyjf.common.message.AjaxPageRequest;
import com.wyjf.common.repository.DrawRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
 * 盘口
 */
@Controller
@RequestMapping("/admin/draw")
public class DrawController {
    private static final Logger log = LoggerFactory.getLogger(DrawController.class);

    @Autowired
    private DrawRepo drawRepo;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "admin/draw/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> listQuery(@ModelAttribute AjaxPageRequest req) {
        List<Draw> users = drawRepo.findAll();
        HashMap map = new HashMap();
        map.put("data", users);
        map.put("draw", req.getDraw());
        map.put("recordsTotal", users.size());
        map.put("recordsFiltered", users.size());
        return map;
    }

}
