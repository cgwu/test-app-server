package com.wyjf.app.api;

import com.wyjf.common.domain.UserInfo;
import com.wyjf.common.message.UserResult;
import com.wyjf.common.repository.UserInfoRepo;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.UUID;


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
    @Autowired
    private UserInfoRepo userInfoRepo;

    @ApiOperation(value = "注册", notes = "用户注册接口", produces = "application/json")
    @RequestMapping(value = {"/reg"}, method = RequestMethod.POST)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pwd", value = "密码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query", dataType = "String")
    })
    public ApiResult reg(@RequestParam String phone, @RequestParam String pwd, @RequestParam String code) {
        LogVerifycode logVerifycode = verfyCodeRepo.findByPhoneExist(phone);
        if (logVerifycode != null && code.equals(logVerifycode.getVerifycode())) {
            User user = userRepo.findByPhone(phone);
            if (user != null) {
                logVerifycode.setStatus(1);
                verfyCodeRepo.save(logVerifycode);
                return ApiFactory.createResult(1, "手机号码已注册", null);
            } else {
                user = new User();
                user.setPhone(phone);
                user.setNickname("U" + phone);
                user.setPasswordLogin(CommonUtil.generatePwd(pwd));
                user.setBalance(0.0);
                user.setTokenTime(CommonUtil.getTokenDateTime(10));
                user.setToken(UUID.randomUUID().toString());
                user = userRepo.save(user);
                logVerifycode.setStatus(1);
                verfyCodeRepo.save(logVerifycode);
                if (user != null) {
                    UserResult userResult = new UserResult(user, null);
                    return ApiFactory.createResult(0, "注册成功", userResult);
                } else {
                    return ApiFactory.createResult(1, "注册失败", null);
                }
            }
        } else {
            return ApiFactory.createResult(1, "验证码错误", null);
        }

    }

    @ApiOperation(value = "登录", notes = "用户登录接口，手机号+密码，手机号+验证码，登录会重新更新token还有过期时间", produces = "application/json")
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pwd", value = "密码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = false, paramType = "query", dataType = "String")
    })
    public ApiResult login(@RequestParam(required = true) String phone, @RequestParam(required = false) String pwd, @RequestParam(required = false) String code) {
        if (CommonUtil.checkEmpty(pwd)) {
            LogVerifycode logVerifycode = verfyCodeRepo.findByPhoneExist(phone);
            if (logVerifycode != null && code.equals(logVerifycode.getVerifycode())) {
                User user = userRepo.findByPhone(phone);
                logVerifycode.setStatus(1);
                verfyCodeRepo.save(logVerifycode);
                if (user != null) {
                    user.setTokenTime(CommonUtil.getTokenDateTime(10));
                    user.setToken(UUID.randomUUID().toString());
                    user = userRepo.save(user);
                    UserInfo userInfo = userInfoRepo.findOne(user.getUid());
                    UserResult userResult = new UserResult(user, userInfo);
                    return ApiFactory.createResult(0, "登录成功", userResult);
                } else {
                    return ApiFactory.createResult(1, "登录失败，未注册", null);
                }
            } else {
                return ApiFactory.createResult(1, "验证码错误", null);
            }
        } else {
            User user = userRepo.findByPhoneOrPwd(phone, CommonUtil.generatePwd(pwd));
            if (user != null) {
                user.setTokenTime(CommonUtil.getTokenDateTime(10));
                user.setToken(UUID.randomUUID().toString());
                user = userRepo.save(user);
                UserInfo userInfo = userInfoRepo.findOne(user.getUid());
                UserResult userResult = new UserResult(user, userInfo);
                return ApiFactory.createResult(0, "登录成功", userResult);
            } else {
                return ApiFactory.createResult(1, "登录失败，未注册或密码错误", null);
            }
        }

    }

    @ApiOperation(value = "获取验证码", notes = "用户获取验证码接口，使用手机号码获取", produces = "application/json")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = {"/verifyCode"}, method = RequestMethod.POST)
    public ApiResult verifyCode(@RequestParam String phone) {
        //获取该电话号码10分钟之内是否存在有效的验证码
        LogVerifycode oldLogVerifycode = verfyCodeRepo.findByPhoneExist(phone);
        if (oldLogVerifycode != null && oldLogVerifycode.getId() != null) {
            return ApiFactory.createResult(0, "获取验证码成功", oldLogVerifycode);
        }
        //生产新的验证码
        String verfycode = CommonUtil.getVerifyCode();
        LogVerifycode v = new LogVerifycode(phone, verfycode);
        v = verfyCodeRepo.save(v);
        if (v.getId() != null) {
            return ApiFactory.createResult(0, "获取验证码成功", v);
        } else {
            return ApiFactory.createResult(1, "获取验证码失败", null);
        }
    }

    @ApiOperation(value = "获取个人信息（需传token）", notes = "使用用户ID获取用户详细信息接口", produces = "application/json")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "token", value = "用户Token", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = {"/getUserInfo"}, method = RequestMethod.POST)
    public ApiResult getUserInfo(@RequestParam String token) {

        if (!checkToken(token)) {
            return ApiFactory.createResult(8, "请重新登陆", null);
        }

        User user;
        UserInfo userinfo;
        try {
            user = userRepo.findByTokenOrTime(token);
            userinfo = userInfoRepo.findOne(user.getUid());
        } catch (Exception e) {
            return ApiFactory.createResult(-1, "获取用户信息失败", null);
        }
        if (user != null && user.getUid() != null) {
            UserResult userResult = new UserResult(user, userinfo);
            return ApiFactory.createResult(0, "获取用户信息成功", userResult);
        } else {
            return ApiFactory.createResult(1, "获取用户信息失败", null);
        }
    }


    @ApiOperation(value = "更新手机号码（需传token）", notes = "使用用户ID更新手机号码接口", produces = "application/json")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "query", dataType = "Int"),
            @ApiImplicitParam(name = "oldPhone", value = "旧手机号码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "newPhone", value = "新手机号码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "用户Token", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = {"/updatePhone"}, method = RequestMethod.POST)
    public ApiResult updatePhone(@RequestParam Integer userId, @RequestParam String oldPhone, @RequestParam String newPhone, @RequestParam String token) {

        if (!checkToken(token)) {
            return ApiFactory.createResult(8, "请重新登陆", null);
        }

        User user = userRepo.findOne(new Long(userId));
        if (user != null) {
            if (user.getPhone().equals(oldPhone)) {
                user.setPhone(newPhone);
                userRepo.save(user);
                return ApiFactory.createResult(0, "修改成功", user);
            } else {
                return ApiFactory.createResult(1, "旧手机号码不匹配", null);
            }
        } else {
            return ApiFactory.createResult(1, "用户不存在", null);
        }
    }

    @ApiOperation(value = "更改登录密码", notes = "使用用户手机和验证码更改登录密码接口", produces = "application/json")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "phone", value = "用户ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "newPwd", value = "新密码", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = {"/updatePwdLogin"}, method = RequestMethod.POST)
    public ApiResult updatePwdLogin(@RequestParam String phone, @RequestParam String code, @RequestParam String newPwd) {
        LogVerifycode logVerifycode = verfyCodeRepo.findByPhoneExist(phone);
        if (logVerifycode != null && code.equals(logVerifycode.getVerifycode())) {
            User user = userRepo.findByPhone(phone);
            if (user != null) {
                user.setPasswordLogin(CommonUtil.generatePwd(newPwd));
                userRepo.save(user);
                return ApiFactory.createResult(0, "修改成功", user);
            } else {
                return ApiFactory.createResult(1, "用户不存在", null);
            }
        } else {
            return ApiFactory.createResult(1, "验证码错误", null);
        }
    }


    @ApiOperation(value = "设置提现密码（需传token）", notes = "用户设置接口", produces = "application/json")
    @RequestMapping(value = {"/updatePwdTrade"}, method = RequestMethod.POST)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pwd", value = "提现密码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "用户Token", required = true, paramType = "query", dataType = "String")
    })
    public ApiResult updatePwdTrade(@RequestParam String phone, @RequestParam String pwd, @RequestParam String code, @RequestParam String token) {

        if (!checkToken(token)) {
            return ApiFactory.createResult(8, "请重新登陆", null);
        }

        LogVerifycode logVerifycode = verfyCodeRepo.findByPhoneExist(phone);
        if (logVerifycode != null && code.equals(logVerifycode.getVerifycode())) {
            User user = userRepo.findByPhone(phone);
            if (user != null) {
                user.setPasswordTrade(CommonUtil.generatePwd(pwd));
                user = userRepo.save(user);
                logVerifycode.setStatus(1);
                verfyCodeRepo.save(logVerifycode);
                return ApiFactory.createResult(0, "修改成功", user);
            } else {
                return ApiFactory.createResult(1, "手机号码不是用户绑定的手机号码", null);
            }
        } else {
            return ApiFactory.createResult(1, "无效验证码", null);
        }

    }

    @ApiOperation(value = "更新用户信息（需传token）", notes = "使用用户ID更新用户信息接口(头像，昵称，性别)", produces = "application/json")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "query", dataType = "Int"),
            @ApiImplicitParam(name = "file", value = "头像字节流", required = false, paramType = "query", dataType = "File"),
            @ApiImplicitParam(name = "nickname", value = "新昵称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "gender", value = "新性别", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "用户Token", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = {"/updateHead"}, method = RequestMethod.POST)
    public ApiResult test(@RequestParam(required = false) MultipartFile file, @RequestParam Integer userId, @RequestParam String nickname, @RequestParam String gender, @RequestParam String token) {

        if (!checkToken(token)) {
            return ApiFactory.createResult(8, "请重新登陆", null);
        }

        try {
            User user = userRepo.findOne(userId.longValue());
            if (!CommonUtil.checkEmpty(nickname)) {
                user.setNickname(nickname);
            }
            if (!CommonUtil.checkEmpty(gender)) {
                user.setGender(gender);
            }
            userRepo.save(user);
            UserInfo userInfo = userInfoRepo.findOne(userId.longValue());
            if (userInfo == null) {
                userInfo = new UserInfo();
                userInfo.setUid(new Long(userId));
            }
            if (file != null) {
                userInfo.setHeadThumb(file.getBytes());
                userInfoRepo.save(userInfo);
            }
            UserResult userResult = new UserResult(user, userInfo);
            return ApiFactory.createResult(0, "保存成功", userResult);
        } catch (Exception e) {
            return ApiFactory.createResult(-1, "保存失败", e.getMessage());
        }
    }

    @ApiOperation(value = "获取用户头像（需传token）", notes = "获取用户头像接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "times", value = "防止url缓存时间戳", required = true, paramType = "path", dataType = "String")
    })
    @RequestMapping(value = {"/head/{userId}/{times}/head.png"}, method = RequestMethod.GET)
    public void getUserHead(@PathVariable Integer userId, HttpServletResponse response, @PathVariable String times) throws Exception {

        UserInfo userinfo = userInfoRepo.getOne(userId.longValue());
        if (userinfo != null) {
            byte[] s = userinfo.getHeadThumb();
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(s);
        }
    }

    public boolean checkToken(String token) {
        User user = userRepo.findByTokenOrTime(token);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

}
