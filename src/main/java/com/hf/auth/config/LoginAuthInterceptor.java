package com.hf.auth.config;

import com.alibaba.fastjson.JSON;
import com.hf.auth.config.annotation.RoleNum;
import com.hf.auth.config.enums.AuthCustomCodeEnum;
import com.hf.auth.config.enums.RoleEnum;
import com.hf.auth.entity.po.UserInfo;
import com.hf.auth.util.AuthTokenUtils;
import com.hf.tools.util.CommonCustomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
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
            request.setAttribute("UUID", uuid);
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            try {
                RoleNum roleNum = handlerMethod.getMethodAnnotation(RoleNum.class);
                String token = request.getHeader("token");
                if (roleNum == null) {
                    if (!StringUtils.isEmpty(token)) {
                        request.setAttribute("userInfo", AuthTokenUtils.analysisToken(uuid, token, UserInfo.class));
                    }
                    return true;
                }
                Method method = handlerMethod.getMethod();
                log.info("uuid={}, method={}, methodName={}", uuid, method, method.getName());
                UserInfo userInfo = AuthTokenUtils.analysisToken(uuid, token, UserInfo.class);
                if (authVerify(roleNum.role(), userInfo.getRoleCode())) {
                    request.setAttribute("userInfo", userInfo);
                    return true;
                } else {
                    log.info("uuid={}, userInfo={}", uuid, JSON.toJSON(userInfo));
                    setResponse(uuid, response, "权限不足", "0198");
                    return false;
                }
            } catch (Exception e) {
                log.error("uuid={}, errMsg={}", uuid, AuthTokenUtils.getStackTraceString(e));
                setResponse(uuid, response, AuthCustomCodeEnum.UNKNOWN_ERROR.getMsg(), "0198");
                return false;
            }
        }
        setResponse(uuid, response, "请求的接口地址不存在", "0198");
        return false;
    }


    /**
     * response 页面错误信息
     *
     * @param uuid     唯一识别码
     * @param response response
     * @param message  错误描述
     * @param msgCode  错误码
     */
    public static void setResponse(Object uuid, HttpServletResponse response, String message, String msgCode) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            Map<String, Object> map = new HashMap<>(2);
            map.put("requestId", uuid);
            map.put("resultDes", message);
            map.put("success", false);
            if (msgCode == null) {
                map.put("code", "0199");
            } else {
                map.put("code", msgCode);
            }
            response.getWriter().append(JSON.toJSONString(map));
        } catch (IOException e) {
            log.error(CommonCustomUtils.LOG_ERROR_OUTPUT_PARAM, uuid, CommonCustomUtils.getStackTraceString(e));
        }
    }

    /**
     * 验证用户权限是否有访问接口的权限
     * 等级类型的验证
     *
     * @param roleEnum 接口上注解设置的权限
     * @param roleCode 用户权限等级
     * @return 是否有权限访问
     */
    private boolean authVerify(RoleEnum roleEnum, Integer roleCode) {
        if (StringUtils.isEmpty(roleCode)) {
            return false;
        }
        return roleCode >= roleEnum.getRoleCode();
    }
}
