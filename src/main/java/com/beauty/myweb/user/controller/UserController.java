package com.beauty.myweb.user.controller;

import com.beauty.myweb.core.annotation.MustLogin;
import com.beauty.myweb.core.annotation.SecurityAccess;
import com.beauty.myweb.core.model.Result;
import com.beauty.myweb.user.dto.LoginDto;
import com.beauty.myweb.user.dto.RegisterDto;
import com.beauty.myweb.user.service.AuthService;
import com.beauty.myweb.user.service.LoginService;
import com.beauty.myweb.user.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private AuthService authService;

    /**
     * 登录
     * @param loginDto
     * @return
     */
    @RequestMapping(value = "/login")
    @MustLogin(false)
    @SecurityAccess(true)
    public Result login(LoginDto loginDto){
        return Result.SUCCESS().setData(loginService.login(loginDto));
    }

    /**
     * 注册
     * @param registerDto
     * @return
     */
    @RequestMapping(value = "/register")
    @MustLogin(false)
    @SecurityAccess(true)
    public Result register(RegisterDto registerDto){
        registerService.register(registerDto);
        return Result.SUCCESS();
    }

    @RequestMapping(value = "/getAuth")
    @MustLogin(false)
    @SecurityAccess(false)
    public Result getAuth(String code){
        return Result.SUCCESS().setData("result",authService.getUserWXOpenId(code));
    }
}
