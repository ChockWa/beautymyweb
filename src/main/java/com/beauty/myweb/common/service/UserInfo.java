package com.beauty.myweb.common.service;

import com.beauty.myweb.core.spring.SpringUtil;
import com.beauty.myweb.system.service.AccessTokenService;
import com.beauty.myweb.user.model.User;
import org.apache.commons.lang3.StringUtils;

public class UserInfo {

    private static final ThreadLocal<User> userInfo = new ThreadLocal<>();

    public static void setUserData(String accessToken){
        if(StringUtils.isBlank(accessToken)){
            return;
        }
        AccessTokenService accessTokenService = SpringUtil.getBean(AccessTokenService.class);
        if(accessTokenService == null){
            accessTokenService = new AccessTokenService();
        }
        userInfo.set(accessTokenService.getUserByToken(accessToken));
    }

    public static User getUser(){
        return userInfo.get();
    }

}
