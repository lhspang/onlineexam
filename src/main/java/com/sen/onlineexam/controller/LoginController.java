package com.sen.onlineexam.controller;

import com.sen.onlineexam.config.shiro.UserToken;
import com.sen.onlineexam.pojo.User;
import com.sen.onlineexam.pojo.UserAuth;
import com.sen.onlineexam.service.QiniuPhoneService;
import com.sen.onlineexam.service.TencentCaptchaService;
import com.sen.onlineexam.service.UserService;
import com.sen.onlineexam.utils.LoginType;
import com.sen.onlineexam.utils.response.ResponseResult;
import com.sen.onlineexam.utils.response.ResultCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping()
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private QiniuPhoneService qiniuPhoneService;
    @Autowired
    private TencentCaptchaService tencentCaptchaService;

    //密码登录
    @PostMapping(value = "login", produces = "application/json;charset=utf-8")
    public ResponseResult loginByPass(String account, String password) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UserToken token = new UserToken(account, password, LoginType.PASSWORD.getName());
            subject.login(token);
            if (subject.isAuthenticated()) {
                User user = (User) subject.getPrincipal();
                user.setLastLoginTime(new Date());
                if (userService.updateUser(user, null, null)) {
                    String id = (String) subject.getSession().getId();
                    User user1 = ((User) subject.getPrincipal());
                    String username = user1.getUserName();
                    String roleName = user1.getRole().getRoleName();
                    Integer userId = user1.getUserId();
                    Map<String, Object> map = new HashMap<>();
                    map.put("Authorization", id);
                    map.put("roleName", roleName);
                    map.put("userId", userId);
                    return ResponseResult.success(map);
                }
            }
            return ResponseResult.failure(ResultCode.USER_ACCOUNT_ERROR);
        } catch (AuthenticationException e) {
            return ResponseResult.failure(ResultCode.USER_ACCOUNT_ERROR);
        }
    }

    //手机验证码登录
    @PostMapping("login/cellphone")
    public ResponseResult loginByPhone(String phone, String code) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UserToken token = new UserToken(phone, code, LoginType.PHONE.getName());
            subject.login(token);
            if (subject.isAuthenticated()) {
                User user = (User) subject.getPrincipal();
                user.setLastLoginTime(new Date());
                if (userService.updateUser(user, null, null)) {
                    String id = (String) subject.getSession().getId();
                    User user1 = ((User) subject.getPrincipal());
                    String username = user1.getUserName();
                    String roleName = user1.getRole().getRoleName();
                    Integer userId = user1.getUserId();
                    Map<String, Object> map = new HashMap<>();
                    map.put("Authorization", id);
                    map.put("roleName", roleName);
                    map.put("userId", userId);
                    return ResponseResult.success(map);
                }
            }
            return ResponseResult.failure(ResultCode.USER_ACCOUNT_ERROR);
        } catch (AuthenticationException e) {
            return ResponseResult.failure(ResultCode.USER_ACCOUNT_ERROR);
        }
    }

    //注册
    @PostMapping("register")
    public ResponseResult register(String username, String phone, String email, String password) {
        if (userService.register(username, phone, email, password)) {
            return ResponseResult.success();
        } else {
            return ResponseResult.failure(ResultCode.SERVER_ERROR);
        }
    }

    //注册前检查用户名。手机号。邮箱是否存在
    @GetMapping("register/check")
    public ResponseResult registerCheckPhone(String account) {
        if (account == null) {
            return ResponseResult.failure(ResultCode.BAD_REQUEST);
        }
        if (userService.registerCheck(account) == null) {
            return ResponseResult.success();
        } else {
            return ResponseResult.failure(ResultCode.USER_HAS_EXIST);
        }
    }

    //为手机号发送验证码
    @GetMapping("captcha/sent")
    public ResponseResult phoneCode(String phone) {
        if (qiniuPhoneService.sentCode(phone)) {
            return ResponseResult.success();
        } else {
            return ResponseResult.failure(ResultCode.PHONE_ERROR);
        }
    }

    //验证用户输入的验证码
    @GetMapping("captcha/verify")
    public ResponseResult verifyPhone(String phone, String code) {
        if (qiniuPhoneService.verifyCode(phone, code)) {
            UserAuth userAuth = userService.login(phone);
            Map<String, Object> map = new HashMap<>();
            map.put("userId", userAuth == null ? "" : userAuth.getUser().getUserId());
            return ResponseResult.success(map);
        } else {
            return ResponseResult.failure(ResultCode.CODE_ERROR);
        }
    }

    //图形验证码
    @GetMapping("captcha")
    public ResponseResult captcha(String ticket, String userIp, String randstr) {
        boolean flag = tencentCaptchaService.captcha(ticket, userIp, randstr);
        if (flag) {
            return ResponseResult.success();
        } else {
            return ResponseResult.failure(ResultCode.CODE_ERROR);
        }
    }

    //用户权限不足，跳转错误页面
    @RequestMapping("autherror")
    public ResponseResult authError(Integer code) {
        if (code == null) {
            return ResponseResult.failure(ResultCode.SERVER_ERROR);
        } else if (code == 1) {
            return ResponseResult.failure(ResultCode.NOT_LOGIN);
        } else {
            return ResponseResult.failure(ResultCode.NOT_AUTHORIZED);
        }
    }
}
