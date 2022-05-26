package com.hf.auth.entity.po;

import com.hf.auth.entity.dto.UserInfoDto;

import java.io.Serializable;

/**
 * @author zhanghf/f5psyche@163.com
 * @version 1.0
 * @date 14:47 2022/5/24
 */
public class UserInfo extends UserInfoDto implements Serializable {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
