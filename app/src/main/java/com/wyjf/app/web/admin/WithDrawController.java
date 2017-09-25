package com.wyjf.app.web.admin;

import com.wyjf.app.service.WithDrawService;
import com.wyjf.common.domain.WithDraw;
import com.wyjf.common.message.WithDrawRequest;
import com.wyjf.common.message.WithDrawResult;
import com.wyjf.common.repository.WithDrawRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/22 0022.
 */
@Controller
@Validated
@RequestMapping("/admin/withdraw")
public class WithDrawController {
    private static final Logger logger = Logger.getLogger(WithDrawController.class);
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private WithDrawRepo withDrawRepo;
    @Autowired
    private WithDrawService withDrawService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "admin/withdraw/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> listQuery(@ModelAttribute WithDrawRequest req) {
        PageRequest pReq = req.getPage(request);
        Map<String, Object> resultMap = withDrawService.findWithDrawList(pReq, request);
        logger.info(resultMap.get("content"));
        HashMap map = new HashMap();
        map.put("data", resultMap.get("content"));
        map.put("draw", req.getDraw());
        map.put("recordsTotal", resultMap.get("count"));
        map.put("recordsFiltered", resultMap.get("count"));
        return map;
    }
}