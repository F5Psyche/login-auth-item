package com.hf.auth.config.enums;

/**
 * 首两位01 代表认证系统
 * 全局自定义错误编码枚举
 *
 * @author zhanghf
 * @version 1.0
 * @date 10:35 2020/3/11
 */
public enum AuthCustomCodeEnum {
    /**
     * 交易请求成功
     */
    SUCCESS("0100", "成功!", "成功"),

    ENTITY_NOT_NULL("0101", "对象不能为空", "%s不能为空"),

    UNKNOWN_ERROR("0199", "未知异常", "异常：%s"),
    ;

    private String code;
    private String msg;
    private String data;


    AuthCustomCodeEnum(String code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getData() {
        return data;
    }
}
