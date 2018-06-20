package com.beauty.myweb.source.controller;

import com.beauty.myweb.core.annotation.MustLogin;
import com.beauty.myweb.core.annotation.SecurityAccess;
import com.beauty.myweb.core.model.Result;
import com.beauty.myweb.source.service.FileService;
import com.sun.corba.se.spi.orbutil.fsm.Guard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @RequestMapping("/uploadFile")
    @MustLogin(false)
    @SecurityAccess(false)
    public Result uploadFile(@RequestParam("file")MultipartFile file){
        if(file != null){
            try {
                fileService.uploadFile("toutiao/20180620",file.getOriginalFilename(),file.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Result.SUCCESS();
    }

    @RequestMapping("/downloadFile")
    @MustLogin(false)
    @SecurityAccess(false)
    public Result downloadFile(){
        return Result.SUCCESS().setData("fileList",fileService.downloadFile("toutiao/20180620/"));
    }
}
