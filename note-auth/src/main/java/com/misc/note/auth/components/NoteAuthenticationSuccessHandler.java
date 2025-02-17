package com.misc.note.auth.components;

import com.alibaba.fastjson.JSON;
import com.misc.note.common.constant.JwtConstant;
import com.misc.note.common.resp.ResultUtil;
import com.misc.note.common.util.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Component
public class NoteAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private RedissonClient redissonClient;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("用户：【{}】登录成功!", authentication.getName());
        if (response.isCommitted()) {
            log.debug("Response has already been committed.");
            return;
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<>();
        authorities.forEach(authority -> roles.add(authority.getAuthority()));
        String accessToken = JwtUtil.createToken(authentication.getName(), roles, JwtConstant.ACCESS_TOKEN, JwtConstant.ACCESS_TOKEN_EXPIRE);
//        RMap<Object, Object> userInfo = redissonClient.getMap(JwtConstant.USER_INFO);
//        userInfo.put(JwtConstant.ACCESS_TOKEN, accessToken);
//        userInfo.put(JwtConstant.ACCESS_TOKEN_EXPIRE_AT, JwtConstant.ACCESS_TOKEN_EXPIRE);
//        userInfo.put(JwtConstant.USERNAME, authentication.getName());
//        userInfo.put(JwtConstant.ROLE, roles);
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        response.setContentType("application/json;charset=utf-8");
        response.addHeader(JwtConstant.AUTHORIZATION, accessToken);
        response.getWriter().write(JSON.toJSONString(ResultUtil.success(accessToken)));
    }
}
