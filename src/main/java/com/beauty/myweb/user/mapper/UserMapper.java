package com.beauty.myweb.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.beauty.myweb.user.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User>{
    int deleteByPrimaryKey(String id);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int getCount();

    User getByName(String userName);

    User getByEmail(String email);
}