package com.beauty.myweb.common.interceptor;

import com.beauty.myweb.common.service.InvokeService;
import com.beauty.myweb.common.service.ParamsService;
import com.beauty.myweb.common.service.UserInfo;
import com.beauty.myweb.core.exception.GlobalExceptionHandler;
import com.beauty.myweb.core.utils.NetWorkUtils;
import com.beauty.myweb.system.constant.LogTypeEnum;
import com.beauty.myweb.system.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.beauty.myweb.system.service.LogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@Component
public class CommonInterceptor implements HandlerInterceptor {

    @Autowired
    private InvokeService invokeService;

    @Autowired
    private ParamsService paramsService;

    @Autowired
    private LogService logService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 处理request的参数
        Map<String,Object> dataMap = paramsService.dueRequestParams(httpServletRequest);
        // 安全校验
        invokeService.checkSaftHandle(httpServletRequest.getRequestURI(),dataMap);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // 解决跨域请求问题
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        // 记录请求日志
//        writeRequestLog(httpServletRequest);
    }

    /**
     * 记录请求日志
     * @param request
     */
    private void writeRequestLog(HttpServletRequest request){
        Log log = new Log();
        log.setLogUrl(request.getRequestURI());
        log.setLogUser(UserInfo.getUser() == null?"":UserInfo.getUser().getId());
        log.setLogType(LogTypeEnum.REQUEST_LOG.getType());
        log.setLogDec(LogTypeEnum.REQUEST_LOG.getDec());
        log.setLogTime(new Date());
        log.setLogIpAddress(NetWorkUtils.getIpAddress(request));
        if(GlobalExceptionHandler.exceptionMsg.get() == null){
            log.setLogStatus("success");
        }else {
            log.setLogStatus(GlobalExceptionHandler.exceptionMsg.get().getMsg());
        }
        logService.addRequestLog(log);
    }
}
