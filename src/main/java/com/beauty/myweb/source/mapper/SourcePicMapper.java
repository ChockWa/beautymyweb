package com.beauty.myweb.source.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.beauty.myweb.source.model.SourcePic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SourcePicMapper extends BaseMapper<SourcePic> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SourcePic record);

    SourcePic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SourcePic record);

    int updateByPrimaryKey(SourcePic record);

    List<SourcePic> getPicsBySourceNo(String sourceNo);
}