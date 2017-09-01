package com.wyjf.app.api;

import com.wyjf.common.domain.User;
import com.wyjf.common.repository.UserRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dannis on 2017/8/22.
 */
@RestController
@RequestMapping(value = "/api/auth")
@Api(description = "授权相关操作")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserRepo userRepo;

    @RequestMapping(value = {"/login/{name}"}, method = RequestMethod.POST)
    @ApiOperation(value = "登陆", notes = "测试日志程序", produces = "application/json")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "path", dataType = "String")
    })
    public List<String> login(@PathVariable String name) {
        log.info("hello,{}", name);
//        User user = userRepo.findOne(2L);
        User user = userRepo.findByNickname(name);
        if (user != null) log.info(user.toString());
        return Arrays.asList(String.format("Hello %s, OK!", name));
    }
}
