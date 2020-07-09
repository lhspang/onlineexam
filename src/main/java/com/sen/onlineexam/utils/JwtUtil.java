package com.sen.onlineexam.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    //有效期2天
    private static final long EXPIRE_TIME = 1000 * 3600 * 24 * 2;
    private static final String SECRET = "zCaKtBnS3eGOHADdxhguhduifghduighsui";

    //创建token
    public static String createToken(int id, String username) {
        try {
            //签发时间
            Date currentTime = new Date();
            //私钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<String, Object>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            return JWT.create()
                    .withIssuer("sen")
                    .withHeader(header)
                    .withClaim("id", id)
                    .withClaim("username", username)
                    .withExpiresAt(new Date(currentTime.getTime() + EXPIRE_TIME))
                    .withIssuedAt(currentTime)
                    .sign(algorithm);
        } catch (IllegalArgumentException | JWTCreationException e) {
            return null;
        }
    }

    //验证token
    public static boolean verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        try {
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("sen").build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    //获取用户id
    public static int getUserId(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("id").asInt();
        } catch (JWTDecodeException e) {
            return 0;
        }
    }
}
