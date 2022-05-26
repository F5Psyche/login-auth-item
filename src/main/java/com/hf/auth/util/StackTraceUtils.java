package com.hf.auth.util;

import com.hf.auth.config.enums.AuthCustomCodeEnum;
import com.hf.auth.config.exception.AuthCustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 展示堆栈信息.
 *
 * @author zhanghf/f5psyche@163.com
 * @version 1.0
 * @date 15:42 2022/5/26
 */
public class StackTraceUtils {

    private StackTraceUtils() {
        throw new AuthCustomException(AuthCustomCodeEnum.UNKNOWN_ERROR, "StackTraceUtils");
    }

    private static Logger log = LoggerFactory.getLogger(StackTraceUtils.class);

    public static void stackTraceElementView() {
        StackTraceElement[] stacks = (new Throwable()).getStackTrace();
        for (StackTraceElement stack : stacks) {
            log.info("methodName={}, fileName={}, className={}, lineNumber={}", stack.getMethodName(), stack.getFileName(), stack.getClassName(), stack.getLineNumber());
        }
    }

//    public static void stackTraceElementView() {
//        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
//        for (StackTraceElement stack : stacks) {
//            log.info("methodName={}, fileName={}, className={}, lineNumber={}", stack.getMethodName(), stack.getFileName(), stack.getClassName(), stack.getLineNumber());
//        }
//    }
}
