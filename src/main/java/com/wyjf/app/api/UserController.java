package com.wyjf.app.api;

import com.wyjf.app.domain.User;
import com.wyjf.app.domain.LogVerifycode;
import com.wyjf.app.repository.UserRepo;
import com.wyjf.app.repository.VerfyCodeRepo;
import com.wyjf.app.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by zhuxulei on 2017/8/24 0024.
 */
@RestController
@RequestMapping(value = "/api/user")
@Api(description = "用户个人信息接口")
public class UserController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private VerfyCodeRepo verfyCodeRepo;

    @ApiOperation(value = "注册", notes = "用户注册接口", produces = "application/json")
    @RequestMapping(value = {"/reg"}, method = RequestMethod.POST)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pwd", value = "密码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query", dataType = "String")
    })
    public ApiResult reg(@RequestParam String phone, @RequestParam String pwd, @RequestParam String code) {
        LogVerifycode logVerifycode = verfyCodeRepo.findByPhoneExist(phone);
        if(logVerifycode != null && code.equals(logVerifycode.getVerifycode())){
            User user = new User();
            user.setNickname(phone);
            user.setPasswordLogin(pwd);
            user = userRepo.save(user);
            if(user != null){
                return ApiFactory.createResult(0, "success", user);
            }else{
                return ApiFactory.createResult(-1, "fail", null);
            }
        }else{
            return ApiFactory.createResult(-1, "fail", null);
        }

    }

    @ApiOperation(value = "登录", notes = "用户登录接口，手机号+密码，手机号+验证码", produces = "application/json")
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pwd", value = "密码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = false, paramType = "query", dataType = "String")
    })
    public ApiResult login(@RequestParam String phone, @RequestParam String pwd, @RequestParam String code) {
        User puser = new User();
        puser.setPasswordLogin(pwd);
        List<User> user = userRepo.findByPhoneOrPwd(phone,pwd);
        if(user != null){
            return ApiFactory.createResult(0, "success", user);
        }else{
            return ApiFactory.createResult(-1, "fail", null);
        }
    }

    @ApiOperation(value = "获取验证码", notes = "用户获取验证码接口，使用手机号码获取", produces = "application/json")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = {"/verifyCode"}, method = RequestMethod.POST)
    public ApiResult verifyCode(@RequestParam String phone){
        //获取该电话号码10分钟之内是否存在有效的验证码
        LogVerifycode oldLogVerifycode = verfyCodeRepo.findByPhoneExist(phone);
        if(oldLogVerifycode != null && oldLogVerifycode.getId() != null){
            return ApiFactory.createResult(0, "success", oldLogVerifycode);
        }
        //生产新的验证码
        String verfycode = CommonUtil.getVerifyCode();
        LogVerifycode v = new LogVerifycode(phone, verfycode);
        v = verfyCodeRepo.save(v);
        if(v.getId() != null){
            return ApiFactory.createResult(0, "success", v);
        }else{
            return ApiFactory.createResult(-1, "fail", null);
        }
    }

    @ApiOperation(value = "获取个人信息", notes = "使用用户ID获取用户详细信息接口", produces = "application/json")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "query", dataType = "Integer")
    })
    @RequestMapping(value = {"/getUserInfo"}, method = RequestMethod.POST)
    public ApiResult getUserInfo(@RequestParam Integer userId){
        User user = userRepo.findOne(new Long(userId));
        user.setUid(new Long(userId));
        user.setNickname("hello");
        return ApiFactory.createResult(user);
    }


}
