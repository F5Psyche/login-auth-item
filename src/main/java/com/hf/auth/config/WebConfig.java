package com.hf.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author zhanghf/f5psyche@163.com
 * @version 1.0
 * @date 15:11 2022/5/23
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    LoginAuthInterceptor loginAuthInterceptor;

    /**
     * /** (匹配所有路径)
     * /admin/** (匹配 /admin/ 下的所有路径)
     *
     * @param registry 注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthInterceptor)
                //拦截
                .addPathPatterns("/**")
                //排除拦截
                .excludePathPatterns("/","/user/login");
    }
}
