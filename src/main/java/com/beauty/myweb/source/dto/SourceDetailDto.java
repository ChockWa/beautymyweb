package com.beauty.myweb.source.dto;

import com.beauty.myweb.source.model.Source;
import com.beauty.myweb.source.model.SourcePic;

import java.util.List;

public class SourceDetailDto extends Source {

    private List<SourcePic> sourcePicList;

    public List<SourcePic> getSourcePicList() {
        return sourcePicList;
    }

    public void setSourcePicList(List<SourcePic> sourcePicList) {
        this.sourcePicList = sourcePicList;
    }
}
