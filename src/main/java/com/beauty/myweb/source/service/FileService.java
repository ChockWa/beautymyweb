package com.beauty.myweb.source.service;

import com.beauty.myweb.common.config.FtpConfigProperties;
import com.beauty.myweb.core.utils.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileService {

    @Autowired
    private FtpConfigProperties fipConfig;

    public void uploadFile(String path,InputStream inputStream){
        Boolean flag = FtpUtil.uploadFile(path, inputStream,fipConfig.getFtpAddress(),fipConfig.getFtpPort(),
                fipConfig.getFtpName(),fipConfig.getFtpPassWord(),fipConfig.getFtpBasePath());
        System.out.println(flag);
        if(inputStream != null){
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
