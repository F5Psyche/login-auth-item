package com.hf.auth.entity.dto;

import java.io.Serializable;

/**
 * @author zhanghf/f5psyche@163.com
 * @version 1.0
 * @date 17:24 2022/5/26
 */
public class UserInfoDto implements Serializable {

    private Integer id;

    private String userName;

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

    public Integer getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(Integer roleCode) {
        this.roleCode = roleCode;
    }
}
