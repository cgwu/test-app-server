package com.wyjf.app.web.admin;

import com.wyjf.app.api.ApiFactory;
import com.wyjf.app.api.ApiResult;
import com.wyjf.app.service.DrawService;
import com.wyjf.common.domain.Draw;
import com.wyjf.common.message.AjaxPageRequest;
import com.wyjf.common.repository.DrawRepo;
import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/5.
 * 盘口
 */
@Controller
@Validated
@RequestMapping("/admin/draw")
public class DrawController {
    private static final Logger log = LoggerFactory.getLogger(DrawController.class);

    @Autowired
    private DrawRepo drawRepo;

    @Autowired
    private DrawService drawService;

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


    @RequestMapping(value = "/addByDate", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult addByDate(@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}") @RequestParam String drawDay) {
        boolean result = drawService.addDrawByDate(LocalDate.parse(drawDay));
        if (result)
            return ApiFactory.success(drawDay + "盘口保存成功!");
        else
            return ApiFactory.fail(-1, drawDay + "盘口保存失败，请勿重复添加!");
    }

}
