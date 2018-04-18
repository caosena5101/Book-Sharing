package com.dyenigma.sharing.exception;

import com.dyenigma.sharing.constant.RespCodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * sharing/com.dyenigma.sharing.exception
 *
 * @Description : 统一返回结果
 * @Author : dingdongliang
 * @Date : 2018/4/18 10:48
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData {

    private Boolean status = true;
    private String code = RespCodeEnum.OK.getCode();
    private String message;
    private Object data;

    public static ResponseData success() {
        return success(null);
    }

    public static ResponseData success(Object data) {
        return new ResponseData(data);
    }

    public ResponseData(Object data) {
        super();
        this.data = data;
    }

    public ResponseData() {
        super();
    }


}
