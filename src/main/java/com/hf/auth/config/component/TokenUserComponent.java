package com.hf.auth.config.component;

import com.hf.auth.entity.po.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhanghf/f5psyche@163.com
 * @version 1.0
 * @date 15:29 2022/5/26
 */
@Component
public class TokenUserComponent {

    private static Logger log = LoggerFactory.getLogger(TokenUserComponent.class);

    public UserInfo userInfoGet(HttpServletRequest request) {
        //StackTraceUtils.stackTraceElementView();
        Object userInfo = request.getAttribute("userInfo");
        if (userInfo == null) {
            return null;
        }
        return (UserInfo) userInfo;
    }
}
