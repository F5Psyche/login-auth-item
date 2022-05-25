package com.hf.auth.config.enums;


/**
 * @author zhanghf
 * @version 1.0
 * @date 16:24 2020/4/15
 */
public enum RoleEnum {
    /**
     * 普通用户
     */
    USER(0, "普通用户"),
    /**
     * 普通管理员
     */
    ADMIN(1, "普通管理员"),
    /**
     * 系统管理员
     */
    SYSTEM_ADMIN(2, "系统管理员"),
    ;

    private Integer roleCode;
    private String role;

    RoleEnum(Integer roleCode, String role) {
        this.roleCode = roleCode;
        this.role = role;
    }

    public static String getRole(Integer roleCode) {
        if (roleCode != null) {
            for (RoleEnum type : RoleEnum.values()) {
                if (type.roleCode.equals(roleCode)) {
                    return type.role;
                }
            }
        }
        return null;
    }

    public static Integer getRoleCode(String role) {
        if (role != null && role.length() > 0) {
            for (RoleEnum type : RoleEnum.values()) {
                if (type.role.equals(role)) {
                    return type.roleCode;
                }
            }
        }
        return null;
    }

    public Integer getRoleCode() {
        return roleCode;
    }

    public String getRole() {
        return role;
    }
}
