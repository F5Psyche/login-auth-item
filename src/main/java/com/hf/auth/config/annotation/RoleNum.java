package com.hf.auth.config.annotation;

import com.hf.auth.config.enums.RoleEnum;

import java.lang.annotation.*;

/**
 * <p>@Retention(RetentionPolicy.SOURCE) //注解仅存在于源码中，在class字节码文件中不包含
 * <p>@Retention(RetentionPolicy.CLASS) //默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
 * <p>@Retention(RetentionPolicy.RUNTIME) //注解会在class字节码文件中存在，在运行时可以通过反射获取到
 * <p>@Target(ElementType.TYPE) //接口、类
 * <p>@Target(ElementType.FIELD) //属性
 * <p>@Target(ElementType.METHOD) //方法
 * <p>@Target(ElementType.PARAMETER) //方法参数
 * <p>@Target(ElementType.CONSTRUCTOR) //构造函数
 * <p>@Target(ElementType.LOCAL_VARIABLE) //局部变量
 * <p>@Target(ElementType.ANNOTATION_TYPE) //注解
 * <p>@Target(ElementType.PACKAGE) //包
 *
 * @author zhanghf
 * @version 1.0
 * @date 15:44 2020/3/25
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleNum {
    RoleEnum role();
}
