package com.beauty.myweb.source.service;

import com.beauty.myweb.common.model.PageInfo;
import com.beauty.myweb.common.model.PageParam;
import com.beauty.myweb.source.mapper.SourceMapper;
import com.beauty.myweb.source.model.Source;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceService {

    @Autowired
    private SourceMapper sourceMapper;

    public PageInfo<Source> getSourcesPage(PageParam pageParam){
        PageHelper.startPage(pageParam.getPageNo(),pageParam.getPageSize());
        Page<Source> page = sourceMapper.getSourcesPage();
        PageInfo<Source> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }
}
