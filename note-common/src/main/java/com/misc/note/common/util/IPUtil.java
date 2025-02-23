package com.misc.note.common.util;

import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;

public class IPUtil {

    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X - Forwarded - For");
        if (Strings.isNullOrEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy - Client - IP");
        }
        if (Strings.isNullOrEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL - Proxy - Client - IP");
        }
        if (Strings.isNullOrEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else {
            // 如果存在多个IP地址（代理链情况），取第一个IP地址
            String[] ips = ip.split(",");
            ip = ips[0].trim();
        }
        return ip;
    }
}
