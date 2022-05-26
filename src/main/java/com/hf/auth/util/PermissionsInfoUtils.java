package com.hf.auth.util;

import com.hf.auth.config.enums.AuthCustomCodeEnum;
import com.hf.auth.config.exception.AuthCustomException;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * 扫描某个路径下拥有某个注解的全部方法
 *
 * @author zhanghf
 * @version 1.0
 * @date 12:36 2020/4/16
 */
public class PermissionsInfoUtils {

    private PermissionsInfoUtils() {
        throw new AuthCustomException(AuthCustomCodeEnum.UNKNOWN_ERROR, "PermissionsInfoUtils");
    }

    private static Logger log = LoggerFactory.getLogger(PermissionsInfoUtils.class);

    /**
     * @param uuid   唯一识别码
     * @param prefix 扫描路径
     */
    public static void prefixInfo(String uuid, String prefix) {

        //扫描com.hf.config 扫描路径不可忘记,不然会扫描全部项目包,包括引用的jar
        Reflections reflections = new Reflections(prefix);
        //获取带Component注解的类
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Component.class);
        for (Class<?> clazz : typesAnnotatedWith) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                //获取该方法上的所有注解
                Annotation[] annotations = method.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    log.info("uuid={}, methodName={}, annotation={}", uuid, methodName, annotation);
                }

            }
        }
    }
}
