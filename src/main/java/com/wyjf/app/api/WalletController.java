package com.wyjf.app.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dannis on 2017/8/22.
 */
@RestController
@RequestMapping(value="/api/wallet")
@Api(description = "钱包相关操作")
public class WalletController {

    @ApiOperation(value="测试", notes="测试操作")
    @RequestMapping(value={"/test"}, method= RequestMethod.GET)
    public List<String> test() {
        List<String> r = Arrays.asList("Buenos Aires", "你好Córdoba", "La Plata");
        return r;
    }

    @ApiOperation(value="测试2", notes="测试操作")
    @RequestMapping(value={"/test"}, method= RequestMethod.POST)
    public List<String> test2() {
        List<String> r = Arrays.asList("1", "2", "3");
        return r;
    }

}
