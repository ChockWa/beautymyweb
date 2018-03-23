package com.beauty.myweb.source.service;

import com.beauty.myweb.common.model.PageInfo;
import com.beauty.myweb.common.model.PageParam;
import com.beauty.myweb.common.service.UserInfo;
import com.beauty.myweb.core.utils.BeanUtils;
import com.beauty.myweb.source.dto.SourceDetailDto;
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

    public PageInfo<Source> getSourcesPage(PageParam pageParam){
        PageHelper.startPage(pageParam.getPageNo(),pageParam.getPageSize());
        Page<Source> page = sourceMapper.getSourcesPage();
        PageInfo<Source> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }

    public SourceDetailDto getSourceDetail(String sourceNo){
        if(StringUtils.isBlank(sourceNo)){
            return null;
        }

        Source source = sourceMapper.getSourceDetail(sourceNo);
        SourceDetailDto sourceDetailDto = BeanUtils.copyToNewBean(source,SourceDetailDto.class);
        if(sourceDetailDto != null){
            sourceDetailDto.setSourcePicList(sourcePicMapper.getPicsBySourceNo(sourceNo));
        }

        // 判断用户是否已经登录,没登录不显示下载信息
        if(UserInfo.getUser() == null){
            sourceDetailDto.setIsLogin(2);
            sourceDetailDto.setDownUrl(null);
            sourceDetailDto.setDownCode(null);
        }else{
            sourceDetailDto.setIsLogin(1);
        }

        return sourceDetailDto;
    }
}
