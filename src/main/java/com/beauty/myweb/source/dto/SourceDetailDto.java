package com.beauty.myweb.source.dto;

import com.beauty.myweb.source.model.Source;

import java.util.List;

public class SourceDetailDto extends Source {

    // 用户是否是vip1-是2-否
    private Integer isVip;

    // 总数
    private Integer total;

    private List<String> sourceList;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public List<String> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<String> sourceList) {
        this.sourceList = sourceList;
    }
}
