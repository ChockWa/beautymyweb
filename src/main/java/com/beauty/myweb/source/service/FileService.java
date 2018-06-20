package com.beauty.myweb.source.service;

import com.beauty.myweb.core.utils.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private FtpUtils ftpUtils;

    public void uploadFile(String path,String fileName, InputStream inputStream){
        ftpUtils.uploadFile(path,fileName,inputStream);
        if(inputStream != null){
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> downloadFile(String path){
        List<String> fileList = ftpUtils.downloadFile(path);
        return fileList;
    }
}
