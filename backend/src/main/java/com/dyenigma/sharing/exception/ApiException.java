package com.dyenigma.sharing.exception;

import com.dyenigma.sharing.constant.RespCodeEnum;

/**
 * sharing/com.dyenigma.sharing.exception
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
