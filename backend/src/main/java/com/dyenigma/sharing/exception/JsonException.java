package com.dyenigma.sharing.exception;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.sharing.constant.RespCodeEnum;
import com.dyenigma.sharing.util.JsonUtil;

/**
 * backend/com.dyenigma.sharing.exception
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/3/11 19:28
 */
public class JsonException extends RuntimeException {
    private JSONObject resultJson;

    /**
     * @param respCodeEnum 以错误的ErrorEnum做参数
     * @return
     * @Description : 调用时可以在任何代码处直接throws这个Exception,都会统一被拦截,并封装好json返回给前台
     * @author dingdongliang
     * @date
     */
    public JsonException(RespCodeEnum respCodeEnum) {
        this.resultJson = JsonUtil.errorJson(respCodeEnum);
    }

    public JsonException(JSONObject resultJson) {
        this.resultJson = resultJson;
    }

    public JSONObject getResultJson() {
        return resultJson;
    }
}
