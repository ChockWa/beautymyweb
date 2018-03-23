package com.beauty.myweb.source.dto;

import com.beauty.myweb.source.model.Source;
import com.beauty.myweb.source.model.SourcePic;

import java.util.List;

public class SourceDetailDto extends Source {

    // 是否已经登录1-是2-否
    private Integer isLogin;

    // 是否是vip1-是2-否
    private Integer isVip;

    private List<SourcePic> sourcePicList;

    public Integer getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(Integer isLogin) {
        this.isLogin = isLogin;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public List<SourcePic> getSourcePicList() {
        return sourcePicList;
    }

    public void setSourcePicList(List<SourcePic> sourcePicList) {
        this.sourcePicList = sourcePicList;
    }
}
