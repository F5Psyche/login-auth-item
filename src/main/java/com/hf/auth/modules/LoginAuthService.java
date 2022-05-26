package com.hf.auth.modules;

import com.hf.auth.entity.po.UserInfo;
import com.hf.auth.util.AuthTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author zhanghf/f5psyche@163.com
 * @version 1.0
 * @date 14:44 2022/5/24
 */
@Service
public class LoginAuthService {

    private static Logger log = LoggerFactory.getLogger(LoginAuthService.class);

    public String userVerify(UUID uuid) {
        long loginTime = System.currentTimeMillis();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setUserName("admin");
        userInfo.setRoleCode(1);
        return AuthTokenUtils.tokenSign(uuid, userInfo, loginTime, null);
    }
}
