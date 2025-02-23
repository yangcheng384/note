package com.misc.note.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.misc.note.common.constant.JwtConstant;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

public class JwtUtil {

    public static String createToken(String username, List<String> roles, String subject, long expires) {
        return JWT.create()
                .withIssuer(JwtConstant.JWT_ISSUER)
                .withSubject(subject)
                .withClaim(JwtConstant.USERNAME, username)
                .withClaim(JwtConstant.ROLE, roles)
                .withExpiresAt(LocalDateTime.now().plusSeconds(expires).toInstant(ZoneOffset.UTC))
                .sign(JwtConstant.ALGORITHM);
    }

    public static Map<String, Claim> verifyToken(String token) {
        JWTVerifier verifier = JWT.require(JwtConstant.ALGORITHM).withIssuer(JwtConstant.JWT_ISSUER).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaims();
    }
}
