package com.hf.auth.config.annotation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * service 层的登录权限aop切面
 *
 * @author zhanghf/f5psyche@163.com
 * @version 1.0
 * @date 14:47 2022/5/23
 */
@Aspect
@Component
public class LoginAuthCustomServiceAop {

    @Before("@annotation(com.hf.auth.config.annotation.LoginAuthCustomService)")
    public void authCustomServiceBefore(JoinPoint joinPoint) {

    }
}
