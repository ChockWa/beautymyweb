package com.beauty.myweb.system.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.beauty.myweb.system.mapper.ConfigDicMapper;
import com.beauty.myweb.system.model.ConfigDic;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfigDicService {

    @Autowired
    private ConfigDicMapper configDicMapper;

    /**
     * 获取所有字典项
     * @return
     */
    public List<ConfigDic> getAllConfigDic(){
        return configDicMapper.getAll();
    }

    /**
     * 根据code获取字典列表
     * @param code
     * @return
     */
    public ConfigDic getDicByCode(String code){
        if(StringUtils.isBlank(code)){
            return null;
        }

        ConfigDic configDic = configDicMapper.getByCode(code);
        return configDic;
    }

    /**
     * 根据类型获取字典列表
     * @param type
     * @return
     */
    public List<ConfigDic> getDicByType(String type){
        if(StringUtils.isBlank(type)){
            return new ArrayList<>();
        }

        List<ConfigDic> list = configDicMapper.getByType(type);
        return list;
    }

    /**
     * 根据id获取字典
     * @param id
     * @return
     */
    public ConfigDic getDicById(Long id){
        if(id == null){
            return null;
        }
        return configDicMapper.selectById(id);
    }


}
