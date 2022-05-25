package com.hf.auth.config;

import com.alibaba.fastjson.JSON;
import com.hf.auth.config.annotation.RoleNum;
import com.hf.auth.entity.po.UserInfo;
import com.hf.auth.util.AuthTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * 自定义注解RoleNum的实现层
 *
 * @author zhanghf
 * @version 1.0
 * @date 16:33 2020/4/15
 */
@Component
public class LoginAuthInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = LoggerFactory.getLogger(LoginAuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        UUID uuid = UUID.randomUUID();
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RoleNum roleNum = handlerMethod.getMethodAnnotation(RoleNum.class);
            if (roleNum == null) {
                return true;
            }
            Integer roleCode = roleNum.role().getRoleCode();
            Method method = handlerMethod.getMethod();
            log.info("uuid={}, method={}, methodName={}", uuid, method, method.getName());
            String token = request.getHeader("token");
            UserInfo userInfo = AuthTokenUtils.analysisToken(uuid, token, UserInfo.class);
            log.info("uuid={}, userInfo={}", uuid, JSON.toJSONString(userInfo));
            setResponse(uuid, response, "失败");
            return false;
        }
        this.setResponse(uuid, response, "请求的接口地址不存在");
        return false;
    }

    private void setResponse(UUID uuid, HttpServletResponse response, String message) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().append(message);
        } catch (IOException e) {
            log.error("uuid={}, errMsg={}", uuid, AuthTokenUtils.getStackTraceString(e));
        }
    }

}
