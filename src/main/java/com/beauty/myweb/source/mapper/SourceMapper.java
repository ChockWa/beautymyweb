package com.beauty.myweb.source.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.beauty.myweb.source.dto.SourceDetailDto;
import com.beauty.myweb.source.model.Source;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SourceMapper extends BaseMapper<Source>{
    int deleteByPrimaryKey(Long id);

    int insertSelective(Source record);

    Source selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Source record);

    int updateByPrimaryKey(Source record);

    Page<Source> getSourcesPage(Source source);

    Source getSourceBySourceNo(String sourceNo);
}