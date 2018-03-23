package com.beauty.myweb.source.exception;

import com.beauty.myweb.core.exception.BizException;

/**
 * 资源相关异常(1109开头)
 */
public class SourceException extends BizException {

    /** 参数有误 */
    public static final SourceException PARAMS_ERROR = new SourceException(11090001,"参数有误");

    /** 您还未登录 */
    public static final SourceException NOT_LOGIN_ERROR = new SourceException(11090002,"您还未登录");

    protected SourceException(int code, String msg) {
        super(code, msg);
    }
}
