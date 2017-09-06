package com.wyjf.app.api;

import com.wyjf.common.repository.StockDataRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/9/4 0004.
 */
@RestController
@RequestMapping(value = "/api/stockdata")
@Api(description = "k线接口")
public class StockDataController {

    @Autowired
    private StockDataRepo stockDataRepo;

    @ApiOperation(value = "分时", notes = "分时K线数据接口", produces = "application/json")
    @RequestMapping(value = {"/mh"}, method = RequestMethod.POST)
    @ApiImplicitParams(
            @ApiImplicitParam(name = "date", value = "日期（格式：2017/09/06）", required = true, paramType = "query", dataType = "String")
    )
    public ApiResult stockdatamh(@RequestParam String date){
        List list = stockDataRepo.findSockDataMin(date);
        return ApiFactory.createResult(list);
    }

    @ApiOperation(value = "时K", notes = "时K线数据接口", produces = "application/json")
    @RequestMapping(value = {"/hk"}, method = RequestMethod.POST)
    @ApiImplicitParams(
            @ApiImplicitParam(name = "date", value = "日期（格式：2017/09/06）", required = true, paramType = "query", dataType = "String")
    )
    public ApiResult stockdatahk(@RequestParam String date){
        List list = stockDataRepo.findSockDataHour(date);
        return ApiFactory.createResult(list);
    }

    @ApiOperation(value = "日K", notes = "日K线数据接口", produces = "application/json")
    @RequestMapping(value = {"/dk"}, method = RequestMethod.POST)
    @ApiImplicitParams(
            @ApiImplicitParam(name = "date", value = "日期（格式：2017/09/06）", required = true, paramType = "query", dataType = "String")
    )
    public ApiResult stockdatadk(@RequestParam String date){
        List list = stockDataRepo.findSockDataDay(date);
        return ApiFactory.createResult(list);
    }


    @ApiOperation(value = "获取参数传进来那天最新1分钟的k数据", notes = "获取参数传进来那天最新1分钟的k数据接口", produces = "application/json")
    @RequestMapping(value = {"/m"}, method = RequestMethod.POST)
    @ApiImplicitParams(
            @ApiImplicitParam(name = "date", value = "日期（格式：2017/09/06）", required = true, paramType = "query", dataType = "String")
    )
    public ApiResult stockdatam(@RequestParam String date){
        List list = stockDataRepo.findSockDataMin(date);
        return ApiFactory.createResult(list.get(0));
    }
    @ApiOperation(value = "获取参数传进来那天最新1小时的k数据", notes = "获取参数传进来那天最新1小时的k数据接口", produces = "application/json")
    @RequestMapping(value = {"/h"}, method = RequestMethod.POST)
    @ApiImplicitParams(
            @ApiImplicitParam(name = "date", value = "日期（格式：2017/09/06）", required = true, paramType = "query", dataType = "String")
    )
    public ApiResult stockdatah(@RequestParam String date){
        List list = stockDataRepo.findSockDataHour(date);
        return ApiFactory.createResult(list.get(0));
    }
    @ApiOperation(value = "获取参数传进来那天最新1天的k数据", notes = "获取参数传进来那天最新1小时的k数据接口", produces = "application/json")
    @RequestMapping(value = {"/d"}, method = RequestMethod.POST)
    @ApiImplicitParams(
            @ApiImplicitParam(name = "date", value = "日期（格式：2017/09/06）", required = true, paramType = "query", dataType = "String")
    )
    public ApiResult stockdatad(@RequestParam String date){
        List list = stockDataRepo.findSockDataDay(date);
        return ApiFactory.createResult(list.get(0));
    }
}
