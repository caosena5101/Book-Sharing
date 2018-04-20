package com.dyenigma.backend.util;

import java.io.UnsupportedEncodingException;

/**
 * backend/com.dyenigma.backend.util
 *
 * @Description : URL处理工具
 * @Author : dingdongliang
 * @Date : 2018/4/11 17:28
 */
public class URLUtils extends org.springframework.web.util.UriUtils {

    private URLUtils() {
    }

    public static String encodeURL(String source, String encoding) throws UnsupportedEncodingException {
        return URLUtils.encode(source, encoding);
    }
}
