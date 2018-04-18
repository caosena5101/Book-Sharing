package com.dyenigma.sharing.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * sharing/com.dyenigma.sharing.exception
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/18 10:53
 */
@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    public ResponseData jsonErrorHandler(HttpServletRequest req, GlobalException e) throws Exception {
        ResponseData r = new ResponseData();
        r.setMessage(e.getMessage());
        r.setCode(e.getCode());
        r.setData(null);
        r.setStatus(false);
        return r;
    }
}