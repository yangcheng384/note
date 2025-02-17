package com.misc.note.common.constant;

import com.auth0.jwt.algorithms.Algorithm;

public class JwtConstant {

    public static final String JWT_SECRET = "cvZ4ILj5eu3HvPRnUTAo";

    public static final String JWT_ISSUER = "NOTE";

    public static final Algorithm ALGORITHM = Algorithm.HMAC256(JWT_SECRET);

    public static final String USERNAME = "username";

    public static final String ROLE = "role";

    public static final String AUTHORIZATION = "Authorization";

    public static final String ACCESS_TOKEN = "access_token";

    public static final String ACCESS_TOKEN_EXPIRE_AT = "access_token_expireAt";

    public static final long ACCESS_TOKEN_EXPIRE = 7 * 24 * 60 * 60;

    public static final String USER_INFO = "user_info";

    public static final String REFRESH_TOKEN = "refresh_token";
}
