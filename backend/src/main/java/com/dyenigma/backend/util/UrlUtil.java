package com.dyenigma.backend.util;

import org.springframework.web.util.UriUtils;

/**
 * backend/com.dyenigma.backend.util
 *
 * @Description : URL处理工具
 * @Author : dingdongliang
 * @Date : 2018/4/11 17:28
 */
public class UrlUtil extends UriUtils {

    private UrlUtil() {
    }

    public static String encodeUrl(String source, String encoding) {
        return UrlUtil.encode(source, encoding);
    }
}
