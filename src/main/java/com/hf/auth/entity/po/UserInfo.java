package com.hf.auth.entity.po;

import java.io.Serializable;

/**
 * @author zhanghf/f5psyche@163.com
 * @version 1.0
 * @date 14:47 2022/5/24
 */
public class UserInfo implements Serializable {

    private Integer id;

    private String userName;

    private String password;

    private Integer roleCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(Integer roleCode) {
        this.roleCode = roleCode;
    }
}
