package com.beauty.myweb.source.controller;


import com.beauty.myweb.common.model.PageParam;
import com.beauty.myweb.core.annotation.MustLogin;
import com.beauty.myweb.core.annotation.SecurityAccess;
import com.beauty.myweb.core.model.Result;
import com.beauty.myweb.source.exception.SourceException;
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

    @RequestMapping("/getSourcesPage")
    @MustLogin(false)
    @SecurityAccess(true)
    public Result getSourcesPage(Source source, PageParam pageParam){
        return Result.SUCCESS().setData(sourceService.getSourcesPage(source, pageParam));
    }

    @RequestMapping("/getSourceDetail")
    @MustLogin(false)
    @SecurityAccess(true)
    public Result getSourceDetail(@RequestParam("sourceNo")String sourceNo){
        return Result.SUCCESS().setData(sourceService.getSourceDetail(sourceNo));
    }

    @RequestMapping("/getSource")
    @MustLogin(false)
    @SecurityAccess(false)
    public Result getSource(String username){
//        throw SourceException.PARAMS_ERROR;
        return Result.SUCCESS().setData("value",sourceService.getSource());
    }
}
