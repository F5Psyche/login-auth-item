package com.hf.auth.config.annotation;

import java.lang.annotation.*;

/**
 * service 层的登录权限aop切面
 *
 * @author zhanghf/f5psyche@163.com
 * @version 1.0
 * @date 14:46 2022/5/23
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginAuthCustomService {
}
