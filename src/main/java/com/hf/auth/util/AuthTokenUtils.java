package com.hf.auth.util;


import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hf.auth.config.enums.AuthCustomCodeEnum;
import com.hf.auth.config.exception.AuthCustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * jwt
 *
 * @author zhanghf
 * @version 1.0
 * @date 15:12 2020/12/25
 */
public class AuthTokenUtils {

    private AuthTokenUtils() {
        throw new AuthCustomException(AuthCustomCodeEnum.UNKNOWN_ERROR, "AuthTokenUtils");
    }

    private static Logger log = LoggerFactory.getLogger(AuthTokenUtils.class);


    /**
     * 密钥
     */
    private static final String SECRET = "rds2#!G9Fds%^&Gg4>aV0@s]E56*Gh^3<Ud8(Rf3}Mxs1$mq5~d7JClR";

    /**
     * key值
     */
    private static final String PAYLOAD = "userInfo";

    /**
     * 默认有效时长（单位毫秒）
     */
    private static final long DEFAULT_VALID_TIME = 60000;


    /**
     * 将异常信息转换为字符串(不换行。如果要换行，将\t换成\n)
     * e.toString() 获取异常名称
     * stackTraceElements获取出现异常的行数、类名、方法名
     *
     * @param e 异常信息
     * @return 字符串
     */
    public static String getStackTraceString(Throwable e) {
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        StringBuilder builder = new StringBuilder();
        builder.append(e.toString());
        if (stackTraceElements != null && stackTraceElements.length > 0) {
            for (StackTraceElement stackTraceElement : stackTraceElements) {
                builder.append("\t at ").append(stackTraceElement.toString());
            }
        }
        return builder.toString();
    }


    /**
     * 生成token
     *
     * @param uuid        唯一识别码
     * @param object      对象
     * @param loginTime   登录时间
     * @param maxVailTime 有效时间
     * @return token
     */
    public static String tokenSign(UUID uuid, Object object, long loginTime, Long maxVailTime) {
        try {
            if (object == null) {
                throw new AuthCustomException(AuthCustomCodeEnum.ENTITY_NOT_NULL, "对象信息");
            }
            long expiresTime = loginTime + (StringUtils.isEmpty(maxVailTime) ? DEFAULT_VALID_TIME : maxVailTime);
            Map<String, Object> headerClaims = new HashMap<>(2);
            headerClaims.put("alg", "HS256");
            headerClaims.put("type", "JWT");
            String data = JSON.toJSONString(object);
            JWTCreator.Builder builder = JWT.create();
            builder.withHeader(headerClaims)
                    .withClaim(PAYLOAD, data)
                    .withExpiresAt(new Date(expiresTime));
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return builder.sign(algorithm);

        } catch (Exception e) {
            log.error("uuid={}, errMsg={}", uuid, getStackTraceString(e));
            throw new AuthCustomException(AuthCustomCodeEnum.UNKNOWN_ERROR, "token生成失败");
        }
    }

    /**
     * token解析
     *
     * @param uuid  唯一识别码
     * @param token token
     * @param clazz 对象属性
     * @param <T>   泛型
     * @return 对象
     */
    public static <T> T analysisToken(Object uuid, String token, Class<T> clazz) {
        try {
            if (StringUtils.isEmpty(token)) {
                throw new AuthCustomException(AuthCustomCodeEnum.ENTITY_NOT_NULL, "token");
            }
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            if (claims.containsKey(PAYLOAD)) {
                String data = claims.get(PAYLOAD).asString();
                return JSON.parseObject(data, clazz);
            } else {
                throw new AuthCustomException(AuthCustomCodeEnum.UNKNOWN_ERROR, "token解析异常");
            }
        } catch (Exception e) {
            log.error("uuid={}, errMsg={}", uuid, getStackTraceString(e));
            throw new AuthCustomException(AuthCustomCodeEnum.UNKNOWN_ERROR, "token解析异常");
        }
    }

}
