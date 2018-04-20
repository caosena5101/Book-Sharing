package com.dyenigma.backend.util;

import com.dyenigma.backend.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * backend/com.dyenigma.backend.util
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/11 17:23
 */
public class ShiroUtil {
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    public static String getUserId() {
        return getUser().getUserId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 获取校验验证码，并清空
     *
     * @param key
     * @return java.lang.String
     * @author dingdongliang
     * @date 2018/4/18 14:42
     */
    public static String getKaptcha(String key) {
        String kaptcha = getSessionAttribute(key).toString();
        getSession().removeAttribute(key);
        return kaptcha;
    }
}
