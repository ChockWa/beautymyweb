package com.beauty.myweb.user.service;

import com.beauty.myweb.common.config.ConfigProperties;
import com.beauty.myweb.common.utils.OKHttpUtil;
import com.beauty.myweb.user.exception.UserException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    // 微信登录授权url
    private static final String WX_LOGIN_AUTH_URL = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private ConfigProperties configProperties;

    public String getUserWXOpenId(String code){
        if(StringUtils.isBlank(code)){
            throw UserException.COMMON_PARAMS_NOT_NULL.format("凭证code");
        }

        Map<String,String> params = new HashMap<>();
        params.put("appid", configProperties.getAppid());
        params.put("secret", configProperties.getSecret());
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        String result = OKHttpUtil.httpGet(WX_LOGIN_AUTH_URL,params);
        System.out.println(result);
        return result;
    }
}
