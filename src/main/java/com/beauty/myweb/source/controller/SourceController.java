package com.beauty.myweb.source.controller;


import com.beauty.myweb.common.model.PageParam;
import com.beauty.myweb.common.service.UserInfo;
import com.beauty.myweb.core.annotation.MustLogin;
import com.beauty.myweb.core.annotation.SecurityAccess;
import com.beauty.myweb.core.model.Result;
import com.beauty.myweb.source.model.Source;
import com.beauty.myweb.source.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SourceController {

    @Autowired
    private SourceService sourceService;

    @RequestMapping("/getSourcesWXPage")
    @MustLogin(false)
    @SecurityAccess(true)
    public Result getSourcesWXPage(Source source, PageParam pageParam){
        return Result.SUCCESS().setData(sourceService.getSourcesWXPage(source, pageParam));
    }

    @RequestMapping("/getSourceWXDetail")
    @MustLogin(false)
    @SecurityAccess(true)
    public Result getSourceWXDetail(@RequestParam("sourceId")Long sourceId){
        return Result.SUCCESS().setData(sourceService.getSourceDetailWX(UserInfo.getUser().getId(),sourceId));
    }

}
