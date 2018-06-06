package com.beauty.myweb.common.utils;

import okhttp3.*;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class OKHttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(OKHttpUtil.class);

    private static OkHttpClient client = null;

    private static OkHttpClient getInstance(){
        if(client == null){
            client = new OkHttpClient.Builder()
                    .connectionPool(new ConnectionPool(15, 5, TimeUnit.MINUTES))
                    .readTimeout(20, TimeUnit.SECONDS)
                    .connectTimeout(20, TimeUnit.SECONDS).build();
            return client;
        }
        return client;
    }
    /**
     * 发起get请求
     *
     * @param url
     * @return
     */
    public static String httpGet(String url) {
        String result = null;
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = getInstance().newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 带参get请求
     * @param url
     * @param params
     * @return
     */
    public static String httpGet(String url, Map<String,String> params) {
        String result = null;
        // 处理参数
        if(MapUtils.isNotEmpty(params)){
            String queryParams = params.entrySet().stream()
                    .map(entry -> String.format("%s=%s", entry.getKey(), entry.getValue()))
                    .collect(Collectors.joining("&"));
            url = String.format("%s%s%s", url, url.contains("?")?"&":"?", queryParams);
        }
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = getInstance().newCall(request).execute();
            result = response.body().string();
            logger.info("{0}请求返回的结果：{1}",new Object[]{url,result});
        } catch (Exception e) {
            logger.error("请求失败",e);
        }
        return result;
    }

    /**
     * 发送httppost请求
     *
     * @param url
     * @return
     */
    public static String httpPost(String url, Map<String,String> params) {
        String result = null;
        String data = "";
        if(MapUtils.isNotEmpty(params)){
            data = params.entrySet().stream().map(entry->String.format("%s=%s", entry.getKey(), entry.getValue())).collect(Collectors
                    .joining("&"));
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/html;charset=utf-8"), data);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        try {
            Response response = getInstance().newCall(request).execute();
            result = response.body().string();
            logger.info("{0}请求返回的结果：{1}",new Object[]{url,result});
        } catch (IOException e) {
            logger.error("请求失败",e);
        }
        return result;
    }
}
