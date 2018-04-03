package com.beauty.myweb.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.beauty.myweb.user.model.VipType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VipTypeMapper extends BaseMapper<VipType>{
    int deleteByPrimaryKey(Long id);

    int insertSelective(VipType record);

    VipType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VipType record);

    int updateByPrimaryKey(VipType record);
}