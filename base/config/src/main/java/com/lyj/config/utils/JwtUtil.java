package com.lyj.config.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lyj.config.exception.TokenAuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lyj
 * @date 2023-07-03
 **/
public class JwtUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    private JwtUtil() {
    }

    /**
     * 密钥
     */
    private static final String SECRET = "lyj";

    /**
     * 过期时间（单位：秒）
     */
    private static final Long EXPIRATION = 2 * 24 * 60 * 60L;

    /**
     * 生成用户token，设置token过期时间
     *
     * @param params Map<String, Object>
     * @return String
     */
    public static String createToken(Map<String, Object> params) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);

        Map<String, Object> header = new HashMap<>(16);
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        //添加头部
        JWTCreator.Builder builder = JWT.create().withHeader(header);
        //将基本信息放入Claims中
        params.forEach((k, v) -> builder.withClaim(k, v.toString()));

        return builder
                //设置过期时间
                .withExpiresAt(expirationDate)
                //签发时间
                .withIssuedAt(new Date())
                //SECRET加密
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static Map<String, Object> verifyToken(String token) {
        DecodedJWT jwt = null;
        HashMap<String, Object> map = new HashMap<>(16);
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
            jwt.getClaims().forEach((k, v) -> map.put(k, v.asString()));
        } catch (IllegalArgumentException | JWTVerificationException e) {
            LOGGER.error("token解析失败: {}", e.getMessage());
            throw new TokenAuthException(e.getMessage());
        }
        return map;
    }
}
