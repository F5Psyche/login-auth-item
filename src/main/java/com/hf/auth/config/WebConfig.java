package com.hf.auth.config;

import com.hf.auth.entity.yml.ExcludeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhanghf/f5psyche@163.com
 * @version 1.0
 * @date 15:11 2022/5/23
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static Logger log = LoggerFactory.getLogger(WebConfig.class);

    @Resource
    LoginAuthInterceptor loginAuthInterceptor;

    @Resource
    ExcludeInfo excludeInfo;

    /**
     * /** (匹配所有路径)
     * /admin/** (匹配 /admin/ 下的所有路径)
     *
     * @param registry 注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> patterns = excludeInfo.getPatterns();
        log.info("patterns={}", patterns);
        if (patterns == null) {
            registry.addInterceptor(loginAuthInterceptor)
                    //拦截
                    .addPathPatterns("/**")
                    //排除拦截
                    .excludePathPatterns("/")
                    .excludePathPatterns("/userInfo/login/**")
                    .excludePathPatterns("/ssoTokenLogin/**")
                    .excludePathPatterns("/file/viewImage/**")
                    .excludePathPatterns("/govDing/**");
        } else {
            registry.addInterceptor(loginAuthInterceptor)
                    //拦截
                    .addPathPatterns("/**")
                    //排除拦截
                    .excludePathPatterns("/")
                    .excludePathPatterns(patterns);
        }
    }
}
