package com.beauty.myweb.user.service;

import com.beauty.myweb.core.utils.InviteCodeUtil;
import com.beauty.myweb.core.utils.SecrityUtils;
import com.beauty.myweb.core.utils.UuidUtil;
import com.beauty.myweb.user.dto.RegisterDto;
import com.beauty.myweb.user.exception.UserException;
import com.beauty.myweb.user.mapper.UserMapper;
import com.beauty.myweb.user.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 注册
     * @param registerDto
     */
    @Transactional
    public void register(RegisterDto registerDto){
        if(registerDto == null || StringUtils.isBlank(registerDto.getPassword()) ||
                (StringUtils.isBlank(registerDto.getEmail()) || StringUtils.isBlank(registerDto.getUserName()))){
            throw UserException.PARAMS_ERROR;
        }

        //  注册通用校验
        this.commonRegisterVerify(registerDto);

        // 检验图形验证码
//        verificationService.checkVerfyCode(registerDto.getBindKey(),registerDto.getVerifyCode());

        // 初始化用户数据
        User newUser = initNewUserDefaultData(registerDto);

        // 进行注册
        userMapper.insert(newUser);
    }

    /**
     * 初始化新用户信息
     * @param registerDto
     * @return
     */
    private User initNewUserDefaultData(RegisterDto registerDto){
        User user = new User();
        user.setId(UuidUtil.genUuidNoLine());
        user.setEmail(registerDto.getEmail());
        user.setSex(registerDto.getSex());
        user.setUserName(registerDto.getUserName());
        String salt = RandomStringUtils.randomAlphabetic(8);
        user.setSalt(salt);
        user.setPassword(SecrityUtils.md5Pwd(salt,registerDto.getPassword()));
        user.setMobile(registerDto.getMobile());
        user.setStatus(1);
        long count = userMapper.getCount();
        user.setInviteCode(InviteCodeUtil.idToCode(count+1));
        return user;
    }

    /**
     * 通用注册校验
     * @param registerDto
     */
    private void commonRegisterVerify(RegisterDto registerDto){
        // 检验用户名是否存在
        if(StringUtils.isNotBlank(registerDto.getUserName())){
            User user = userMapper.getByName(registerDto.getUserName());
            if(user != null){
                throw UserException.NAME_ISEXIST_ERROR;
            }
        }
        // 检验邮箱是否已经存在
        if(StringUtils.isNotBlank(registerDto.getEmail())){
            User user = userMapper.getByEmail(registerDto.getEmail());
            if(user != null){
                throw UserException.EMAIL_ISEXIST_ERROR;
            }
        }
    }
}
