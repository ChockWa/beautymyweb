package com.beauty.myweb.source.service;

import com.beauty.myweb.common.model.PageInfo;
import com.beauty.myweb.common.model.PageParam;
import com.beauty.myweb.common.service.UserInfo;
import com.beauty.myweb.core.utils.BeanUtils;
import com.beauty.myweb.source.dto.SourceDetailDto;
import com.beauty.myweb.source.exception.SourceException;
import com.beauty.myweb.source.mapper.SourceMapper;
import com.beauty.myweb.source.mapper.SourcePicMapper;
import com.beauty.myweb.source.model.Source;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceService {

    @Autowired
    private SourceMapper sourceMapper;

    @Autowired
    private SourcePicMapper sourcePicMapper;

    public PageInfo<Source> getSourcesPage(Source source, PageParam pageParam){
        PageHelper.startPage(pageParam.getPageNo(),pageParam.getPageSize());
        Page<Source> page = sourceMapper.getSourcesPage(source);
        PageInfo<Source> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }

    public SourceDetailDto getSourceDetail(String sourceNo){
        if(StringUtils.isBlank(sourceNo)){
            return null;
        }

        Source source = sourceMapper.getSourceBySourceNo(sourceNo);
        SourceDetailDto sourceDetailDto = BeanUtils.copyToNewBean(source,SourceDetailDto.class);
        if(sourceDetailDto != null){
            sourceDetailDto.setSourcePicList(sourcePicMapper.getPicsBySourceNo(sourceNo));
        }

        return sourceDetailDto;
    }

    public Source getDownloadMsg(String sourceNo){
        if(StringUtils.isBlank(sourceNo)){
            throw SourceException.PARAMS_ERROR;
        }

        if(UserInfo.getUser() == null){
            throw SourceException.NOT_LOGIN_ERROR;
        }

        return sourceMapper.getSourceBySourceNo(sourceNo);
    }

    public String getSource(){
        return "source";
    }
}
