package com.dyenigma.backend.exception;

import com.dyenigma.backend.constant.RespCodeEnum;

/**
 * sharing/com.dyenigma.backend.exception
 *
 * @Description : RESTFULL API异常类
 * @Author : dingdongliang
 * @Date : 2018/4/18 11:32
 */
public class ApiException extends GlobalException {

    public ApiException(RespCodeEnum respCodeEnum) {
        super(respCodeEnum.getMessage(), respCodeEnum.getCode());
    }
}
