package com.sdc.factor.entity.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sdc.factor.entity.common.enums.SysUserType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Jwt 帮助类
 *
 * @author Sean
 * @since 2019-04-06
 */
public class JwtUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    public static final String USER_ID = "uid";
    public static final String USER_TYPE = "utype";
    public static final String APP_ID = "app_id";

    /**
     * 检查token是否过期
     * @param expiration token过期时间
     * @return 是否已经过期
     */
    public static boolean isTokenExpired(@NotNull Date expiration) {
        return expiration.before(new Date());
    }

    /**
     * 校验token是否正确
     *
     * @param token 密钥
     * @param userId 用户id
     * @param secret 加密密钥
     * @return token是否正确
     */
    public static boolean verify(String token, long userId, String secret, SysUserType sysUserType) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(USER_ID, userId)
                    .withClaim(USER_TYPE, sysUserType.getValue())
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            LOGGER.error("Fail tp verify the token with value: " + token);
            LOGGER.error("The target user id for token verification is " + userId);
            LOGGER.error("JWT Token verify failed", exception);
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得的用户id
     *
     * @param token 密钥
     * @return token中包含的用户id
     */
    public static Long getUserId(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        } else {
            try {
                DecodedJWT jwt = JWT.decode(token);
                return jwt.getClaim(USER_ID).asLong();
            } catch (JWTDecodeException e) {
                LOGGER.error("Fail to get user id from JWT token with value: " + token, e);
                return null;
            }
        }
    }

    /**
     * 获得用户的类型
     * @param token 密钥
     * @return token中包含的用户类型
     */
    public static SysUserType getUserType(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        } else {
            try {
                DecodedJWT jwt = JWT.decode(token);
                return EnumUtils.valueOf(SysUserType.class, jwt.getClaim(USER_TYPE).asString());
            } catch (JWTDecodeException e) {
                LOGGER.error("Fail to get user type from JWT token with value: " + token, e);
                return null;
            }
        }
    }

    /**
     * 获得token的过期时间
     * @param token 密钥
     * @return token的过期时间
     */
    public static Date getTokenExpireAt(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        } else {
            try {
                DecodedJWT jwt = JWT.decode(token);
                return jwt.getExpiresAt();
            } catch (JWTDecodeException e) {
                LOGGER.error("Fail to get expire time from JWT token with value: " + token, e);
                return null;
            }
        }
    }

    /**
     * 生成签名,指定时间后过期,一经生成不可修改，令牌在指定时间内一直有效
     *
     * @param userId 用户的id
     * @param secret 加密盐
     * @param expireTime token过期时间，单位为秒
     * @param sysUserType 用户类型
     * @return jwt token
     *
     * @see SysUserType
     */
    public static String sign(long userId, String secret, long expireTime, SysUserType sysUserType) {
        try {
            Date date = new Date(System.currentTimeMillis() + expireTime * 1000);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim(USER_ID, userId)
                    .withClaim(USER_TYPE, sysUserType.getValue())
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Fail to generate the jwt token for user with id " + userId, e);
            return "";
        }
    }

    /**
     * 生成ticket
     * @param userId 已登录用户的id
     * @param appId 第三方系统的appid
     * @param appSecret 第三方系统的appsecret
     * @param expireDuration ticket的过期时间
     * @return jwt格式的ticket
     */
    public static String generateTicket(long userId, String appId, String appSecret, long expireDuration) {
        try {
            Date date = new Date(System.currentTimeMillis() + expireDuration * 1000);
            Algorithm algorithm = Algorithm.HMAC256(appSecret);
            return JWT.create()
                    .withClaim(USER_ID, String.valueOf(userId))
                    .withClaim(APP_ID, appId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Fail to generate ticket for user with id " + userId);
            return "";
        }
    }

    /**
     * 获得Claim的值
     * @param token
     * @param claimKey
     * @return
     */
    public static String getTokenClaim(String token, String claimKey) {
        if (StringUtils.isBlank(token)) {
            return null;
        } else {
            try {
                DecodedJWT jwt = JWT.decode(token);
                return jwt.getClaim(claimKey).asString();
            } catch (JWTDecodeException e) {
                LOGGER.error("Fail to get " + claimKey + " from JWT token with value: " + token, e);
                return null;
            }
        }
    }
}
