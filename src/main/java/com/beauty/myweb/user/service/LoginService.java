package com.beauty.myweb.user.service;

import com.beauty.myweb.core.utils.RegxUtils;
import com.beauty.myweb.core.utils.SecrityUtils;
import com.beauty.myweb.system.service.AccessTokenService;
import com.beauty.myweb.user.dto.LoginDto;
import com.beauty.myweb.user.dto.LoginResultDto;
import com.beauty.myweb.user.exception.UserException;
import com.beauty.myweb.user.mapper.UserMapper;
import com.beauty.myweb.user.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AccessTokenService accessTokenService;

    @Transactional
    public LoginResultDto login(LoginDto loginDto){
        if(loginDto == null || StringUtils.isBlank(loginDto.getAccount()) || StringUtils.isBlank(loginDto.getPassword())){
            throw UserException.PARAMS_ERROR;
        }

        User user = null;

        if(RegxUtils.isEmail(loginDto.getAccount())){
            // 邮箱登录
            user = userMapper.getByEmail(loginDto.getAccount());
        }else{
            // 用户名登录
            user = userMapper.getByName(loginDto.getAccount());
        }

        if(user == null){
            throw UserException.USER_ISEXIST_ERROR;
        }else{
            if(1 != user.getStatus()){
                throw UserException.USER_STATE_ERROR;
            }
        }

        // 校验图形验证码
//        verificationService.checkVerfyCode(loginDto.getBindKey(),loginDto.getVerifyCode());

        // 校验密码
        if(!checkPassword(loginDto.getPassword(),user.getSalt(),user.getPassword())){
            throw UserException.LOGIN_PASSWORD_ERROR;
        }

        // 登录
        String accessToken = accessTokenService.generateToken(user.getId());
        LoginResultDto loginResultDto = new LoginResultDto();
        loginResultDto.setAccessToken(accessToken);
        return loginResultDto;
    }

    /**
     * 检验密码
     * @param inputPassword
     * @param salt
     * @param DBPassword
     * @return
     */
    private boolean checkPassword(String inputPassword,String salt,String DBPassword){
        if(StringUtils.isBlank(inputPassword) || StringUtils.isBlank(DBPassword)){
            return false;
        }

        String input = SecrityUtils.md5Pwd(salt,inputPassword);
        if(!input.equals(DBPassword)){
            return false;
        }
        return true;
    }
}
