package com.beauty.myweb.source.service;

import com.beauty.myweb.common.model.PageInfo;
import com.beauty.myweb.common.model.PageParam;
import com.beauty.myweb.core.utils.BeanUtils;
import com.beauty.myweb.source.dto.SourceDetailDto;
import com.beauty.myweb.source.exception.SourceException;
import com.beauty.myweb.source.mapper.SourceMapper;
import com.beauty.myweb.source.model.Source;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class SourceService {

    @Autowired
    private SourceMapper sourceMapper;

    /**
     * 分页获取资源列表
     * @param source
     * @param pageParam
     * @return
     */
    public PageInfo<Source> getSourcesWXPage(Source source, PageParam pageParam){
        source.setIsLegal(1);
        PageHelper.startPage(pageParam.getPageNo(),pageParam.getPageSize());
        Page<Source> page = sourceMapper.selectSelectivePage(source);
        List<Source> sourceList = page.getResult();
        sourceList.forEach(s -> {
            // 获取展示的第一张图片
            s.setImageUrls(s.getImageUrls().split(",")[0]);
        });
        PageInfo<Source> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }

    /**
     * 获取资源详情
     * @param uid
     * @param id
     * @return
     */
    public SourceDetailDto getSourceDetailWX(String uid, Long id){
        if(null == id){
            throw SourceException.COMMON_PARAMS_NOT_NULL.format("id");
        }

        Source source = sourceMapper.selectByPrimaryKey(id);
        List<String> picsList = Lists.newArrayList(source.getImageUrls().split(","));
        int len = picsList.size();
        if(StringUtils.isBlank(uid)){
            // 如果没有登录，只保留前三张图
            if(3 < len){
                picsList = picsList.subList(0,3);
            }
        }
        source.setImageUrls(null);
        SourceDetailDto detailDto = BeanUtils.copyToNewBean(source,SourceDetailDto.class);
        detailDto.setIsVip(2);
        detailDto.setTotal(len - 3);
        detailDto.setSourceList(picsList);
        return detailDto;
    }

}
