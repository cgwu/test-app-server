package com.wyjf.app.api;

import com.wyjf.common.domain.Draw;
import com.wyjf.common.repository.DrawRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Administrator on 2017/9/12.
 */
@RestController
@RequestMapping(value = "/api/draw")
@Api(description = "盘口接口")
public class DrawApiController {

    @Autowired
    private DrawRepo drawRepo;

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

}
