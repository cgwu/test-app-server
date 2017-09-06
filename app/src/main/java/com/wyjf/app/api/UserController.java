package com.wyjf.app.api;

import com.wyjf.common.util.CommonUtil;
import com.wyjf.common.domain.LogVerifycode;
import com.wyjf.common.domain.User;
import com.wyjf.common.repository.LogVerifyCodeRepo;
import com.wyjf.common.repository.UserRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    private LogVerifyCodeRepo verfyCodeRepo;

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
            user.setPhone(phone);
            user.setNickname("U"+phone);
            user.setPasswordLogin(CommonUtil.generatePwd(pwd));
            user.setBalance(0.0);
            user = userRepo.save(user);
            logVerifycode.setStatus(1);
            verfyCodeRepo.save(logVerifycode);
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
            @ApiImplicitParam(name = "pwd", value = "密码", required = false, paramType = "query", dataType = "String")
    })
    public ApiResult login(@RequestParam String phone, @RequestParam String pwd) {
        User user = userRepo.findByPhoneOrPwd(phone,CommonUtil.generatePwd(pwd));
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
        if(user.getUid() != null){
            return ApiFactory.createResult(0, "success", user);
        }else{
            return ApiFactory.createResult(-1, "fail", null);
        }
    }


    @ApiOperation(value = "更新手机号码", notes = "使用用户ID更新手机号码接口", produces = "application/json")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "oldPhone", value = "旧手机号码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "newPhone", value = "新手机号码", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = {"/updatePhone"}, method = RequestMethod.POST)
    public ApiResult updatePhone(@RequestParam Integer userId, @RequestParam String oldPhone, @RequestParam String newPhone){
        User user = userRepo.findOne(new Long(userId));
        if(user != null) {
            if (user.getPhone().equals(oldPhone)) {
                user.setPhone(newPhone);
                userRepo.save(user);
                return ApiFactory.createResult(0, "修改成功", user);
            } else {
                return ApiFactory.createResult(-1, "旧手机号码不匹配", null);
            }
        } else {
            return ApiFactory.createResult(-1, "用户不存在", null);
        }
    }

    @ApiOperation(value = "更新用户信息", notes = "使用用户ID更新用户信息接口(头像，昵称，性别)", produces = "application/json")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "img", value = "新头像", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "nickname", value = "新昵称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "gender", value = "新性别", required = false, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = {"/updateUserInfo"}, method = RequestMethod.POST)
    public ApiResult updateUserInfo(@RequestParam Integer userId, @RequestParam String img, @RequestParam String nickname, @RequestParam String gender){
        User user = userRepo.findOne(new Long(userId));
        if(user != null) {
            if(!CommonUtil.checkEmpty(img)) {

            }
            if(!CommonUtil.checkEmpty(nickname)) {
                user.setNickname(nickname);
            }
            if(!CommonUtil.checkEmpty(gender)) {
                user.setGender(gender);
            }
            userRepo.save(user);
            return ApiFactory.createResult(0, "修改成功", user);
        } else {
            return ApiFactory.createResult(-1, "用户不存在", null);
        }
    }


    @ApiOperation(value = "设置提现密码", notes = "用户设置接口", produces = "application/json")
    @RequestMapping(value = {"/updatePwdTrade"}, method = RequestMethod.POST)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pwd", value = "提现密码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query", dataType = "String")
    })
    public ApiResult updatePwdTrade(@RequestParam Integer userId, @RequestParam String phone, @RequestParam String pwd, @RequestParam String code) {
        LogVerifycode logVerifycode = verfyCodeRepo.findByPhoneExist(phone);
        if(logVerifycode != null && code.equals(logVerifycode.getVerifycode())){
            User user = userRepo.findOne(new Long(userId));
            user.setPasswordTrade(CommonUtil.generatePwd(pwd));
            user = userRepo.save(user);
            logVerifycode.setStatus(1);
            verfyCodeRepo.save(logVerifycode);
            if(user != null){
                return ApiFactory.createResult(0, "修改成功", user);
            }else{
                return ApiFactory.createResult(-1, "修改失败", null);
            }
        }else{
            return ApiFactory.createResult(-1, "无效验证码", null);
        }

    }



}
