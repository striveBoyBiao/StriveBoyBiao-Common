package com.zizhuling.common.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * <p>
 *     获取IP
 * </P>
 *
 * @author hebiao Create on 2020/7/11 18:42
 * @version 1.0
 */
public class NetUtils {
    private NetUtils() {

    }

    public static String getRemoteIP(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡获取IP
                try {
                    ipAddress = InetAddress.getLocalHost().getHostAddress();
                } catch (Exception e) {

                }
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) {
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(','));
            }
        }
        return ipAddress;
    }
}
