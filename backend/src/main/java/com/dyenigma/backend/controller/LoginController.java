package com.dyenigma.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.backend.constant.RespCodeEnum;
import com.dyenigma.backend.exception.ApiException;
import com.dyenigma.backend.exception.GlobalException;
import com.dyenigma.backend.exception.ResponseData;
import com.dyenigma.backend.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * backend/com.dyenigma.backend.controller
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/10 7:50
 */
@RestController
@Api(value = "P2P图书馆-用户登录接口（登录验证和退出）")
@Slf4j
public class LoginController {

    /**
     * 登录验证
     *
     * @param requestJson
     * @return com.dyenigma.backend.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 14:22
     */
    @ApiOperation(value = "用户登陆方法", notes = "详细说明文档")
    @PostMapping("/login")
    public ResponseData authLogin(@ApiParam(name = "requestJson",
            value = "格式为{\"username\": \"admin\"," + "\"password\": \"admin\"}", required = true)
                                  @RequestBody JSONObject requestJson) throws GlobalException {
        JsonUtil.hasAllRequired(requestJson, "username,password");

        String account = requestJson.getString("username");
        String password = requestJson.getString("password");

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(
                account, password);
        try {
            if (!currentUser.isAuthenticated()) {
                currentUser.login(token);
            }
            log.debug("对用户[{}]进行登录验证..验证通过", account);

        } catch (UnknownAccountException e) {
            log.error(RespCodeEnum.NO_ACCOUNT.getMessage());
            throw new ApiException(RespCodeEnum.NO_ACCOUNT);
        } catch (IncorrectCredentialsException e) {
            log.error(RespCodeEnum.PWD_ERROR + e.getMessage());
            throw new ApiException(RespCodeEnum.PWD_ERROR);
        } catch (LockedAccountException e) {
            log.error(RespCodeEnum.ACCOUNT_LOCKED.getMessage());
            throw new ApiException(RespCodeEnum.ACCOUNT_LOCKED);
        } catch (ExcessiveAttemptsException e) {
            log.error(RespCodeEnum.TRY_MORE_FIVE.getMessage());
            throw new ApiException(RespCodeEnum.TRY_MORE_FIVE);
        } catch (DisabledAccountException e) {
            log.error(RespCodeEnum.ACCOUNT_FORBID.getMessage());
            throw new ApiException(RespCodeEnum.ACCOUNT_FORBID);
        } catch (AuthenticationException e) {
            log.error(RespCodeEnum.STH_ERROR.getMessage());
            throw new ApiException(RespCodeEnum.STH_ERROR);
        }

        return ResponseData.success();
    }


    /**
     * 登出，会触发退出事件，清空用户状态，见ShiroRealm中的onLogout方法
     *
     * @return com.dyenigma.backend.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 14:23
     */
    @PostMapping("/logout")
    public ResponseData logout() throws GlobalException {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException(RespCodeEnum.SERVER_ERROR);
        }
        return ResponseData.success();
    }
}

