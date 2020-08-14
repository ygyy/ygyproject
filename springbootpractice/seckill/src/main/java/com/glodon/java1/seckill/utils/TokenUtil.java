package com.glodon.java1.seckill.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class TokenUtil {
    private static final long EXPIRE_TIME = 10 * 60 * 60 * 1000;
    private static final String TOKEN_SECRET = "txdy";  //密钥盐

    public static String sign(String username) {
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("username", username)
                    .withExpiresAt(expiresAt)
                    .sign(com.auth0.jwt.algorithms.Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(com.auth0.jwt.algorithms.Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("username: " + jwt.getClaim("username").asString());
            System.out.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
