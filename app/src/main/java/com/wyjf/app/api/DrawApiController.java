package com.wyjf.app.api;

import com.wyjf.app.service.DrawService;
import com.wyjf.app.service.SystemParamService;
import com.wyjf.app.service.TicketService;
import com.wyjf.common.domain.Draw;
import com.wyjf.common.domain.User;
import com.wyjf.common.message.ApiCode;
import com.wyjf.common.message.DrawEx;
import com.wyjf.common.repository.DrawRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Created by Administrator on 2017/9/12.
 */
@RestController
@RequestMapping(value = "/api/draw")
@Api(description = "盘口接口")
public class DrawApiController {

    @Autowired
    private SystemParamService systemParamService;

    @Autowired
    private DrawRepo drawRepo;

    @Autowired
    private DrawService drawService;

    @Autowired
    private TicketService ticketService;

    /*
    @RequestMapping(value = {"/{date}"}, method = RequestMethod.GET)
    @ApiImplicitParams(
            @ApiImplicitParam(name = "date", value = "日期（格式：2017-09-12）", required = true, paramType = "path", dataType = "String")
    )
    @ApiOperation(value = "查询某天所有盘口", notes = "查询某天盘口信息列表接口", produces = "application/json")
    public ApiResult day(@PathVariable String date) {
        List<Draw> list = drawRepo.findByDrawDay(LocalDate.parse(date));
        return ApiFactory.createResult(list);
    }

    @RequestMapping(value = {"/{date}/{index}"}, method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期(格式：2017-09-12)", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "index", value = "1-5(5代表全天)", required = true, paramType = "path", dataType = "Int")
    })
    @ApiOperation(value = "查询某天某个盘口", notes = "查询某天某个盘口详细接口", produces = "application/json")
    public ApiResult dayIndex(@PathVariable String date, @PathVariable int index) {
        List<Draw> list = drawRepo.findByDrawDayAndDrawSeq(LocalDate.parse(date), index);
        if (list.size() > 0)
            return ApiFactory.createResult(list.get(0));
        else
            return ApiFactory.fail(2, "无相关记录");
    }
    */

    @RequestMapping(value = {"/query"}, method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期(格式：2017-09-12)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "index", value = "1-5(5代表全天)", required = false, paramType = "query", dataType = "Int")
    })
    @ApiOperation(value = "查询某天某个盘口", notes = "查询某天某个盘口详细接口", produces = "application/json")
    public ApiResult query(@RequestParam String date, @RequestParam(required = false) Integer index) {
        int beforeMins = systemParamService.getBeforeBuyMins();
        if (index == null) {
            //查全天
            List<DrawEx> listEx = new ArrayList<>(5);
            List<Draw> list = drawRepo.findByDrawDay(LocalDate.parse(date));

            LocalDateTime now = LocalDateTime.now();
            for (Draw d : list) {
                LocalDateTime deadline = d.getStartDate().minus(beforeMins, ChronoUnit.MINUTES);
                long countdown = 0;
//                if (now.isBefore(deadline)) {
                Duration du = Duration.between(now, deadline);
                countdown = du.toMillis();
//                }
                DrawEx dex = new DrawEx(d, countdown);
                listEx.add(dex);
            }
            return ApiFactory.createResult(listEx);
        } else {
            List<Draw> list = drawRepo.findByDrawDayAndDrawSeq(LocalDate.parse(date), index);
            if (list.size() > 0) {
                Draw d = list.get(0);
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime deadline = d.getStartDate().minus(beforeMins, ChronoUnit.MINUTES);
                long countdown = 0;
//                if (now.isBefore(deadline)) {
                Duration du = Duration.between(now, deadline);
                countdown = du.toMillis();
//                }
                DrawEx dex = new DrawEx(d, countdown);
                return ApiFactory.createResult(dex);
            } else
                return ApiFactory.fail(2, "无相关记录");
        }
    }

    @RequestMapping(value = {"/recent"}, method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "开始日期(格式：2017-09-12)", required = true, paramType = "query", dataType = "String"),
    })
    @ApiOperation(value = "查询今后5天盘口日期", notes = "查询今后5天盘口日期", produces = "application/json")
    public ApiResult query(@RequestParam String date) {
        List<Date> ld = drawRepo.findRecentDrawDate(date, 5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E");
        /*
        Map<LocalDate, String> map = new LinkedHashMap<LocalDate, String>();
        for (Date d : ld) {
            map.put(d.toLocalDate(), d.toLocalDate().format(formatter));
        }
        */
        List<String> list = new ArrayList();
        for (Date d : ld) {
            list.add(d.toLocalDate().toString() + " " + d.toLocalDate().format(formatter));
        }
        return ApiFactory.createResult(list);
    }


    @RequestMapping(value = {"/detail"}, method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "did", value = "盘口ID", required = true, paramType = "query", dataType = "Long"),
    })
    @ApiOperation(value = "查看盘口详细", notes = "查询盘口详细信息接口(status:0未结账;1已结账),返回状态码code:\n" +
            "*  0: 成功\n",
            produces = "application/json")
    public ApiResult detail(
            @RequestParam long did
    ) {
        Map<String, Object> map = drawService.detail(did);
        List<Map<String, Object>> list123 = ticketService.find123prize(did);
        if (map != null) map.put("prize123", list123);
        return ApiFactory.createResult(map);
    }

}
