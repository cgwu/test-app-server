package com.wyjf.app.web.admin;

import com.querydsl.core.types.Predicate;
import com.wyjf.app.api.ApiFactory;
import com.wyjf.app.api.ApiResult;
import com.wyjf.app.service.DrawResultService;
import com.wyjf.app.service.DrawService;
import com.wyjf.common.domain.Draw;
import com.wyjf.common.domain.QDraw;
import com.wyjf.common.message.AjaxPageRequest;
import com.wyjf.common.message.DrawQueryRequest;
import com.wyjf.common.repository.DrawRepo;
import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    private HttpServletRequest http;

    @Autowired
    private DrawRepo drawRepo;

    @Autowired
    private DrawService drawService;

    @Autowired
    DrawResultService drawResultService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "admin/draw/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> listQuery(@ModelAttribute DrawQueryRequest req) {
        LocalDate drawDay = LocalDate.now();
        Predicate predicate = null;
        if (!StringUtils.isEmpty(req.getDrawDay())) {
            drawDay = LocalDate.parse(req.getDrawDay());
            predicate = QDraw.draw.drawDay.eq(drawDay);
        } else
            predicate = QDraw.draw.drawDay.goe(drawDay);

        PageRequest pReq = req.getPage(http);

        Page<Draw> rows = drawRepo.findAll(predicate, pReq);
        HashMap map = new HashMap();
        map.put("data", rows.getContent());
        map.put("draw", req.getDraw());
        map.put("recordsTotal", rows.getTotalElements());
        map.put("recordsFiltered", rows.getTotalElements());
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

    @RequestMapping(value = "/process/{drawId}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult process(@NotNull @PathVariable Long drawId) {
        Pair<Integer, String> res = drawResultService.process(drawId);
        return ApiFactory.createResult(res.getFirst(), res.getSecond(), null);
    }

}
