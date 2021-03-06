package com.beauty.myweb.source.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.beauty.myweb.source.model.Source;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SourceMapper extends BaseMapper<Source>{
    int deleteByPrimaryKey(Long id);

    Source selectByPrimaryKey(Long id);

    Page<Source> selectSelectivePage(Source source);

}