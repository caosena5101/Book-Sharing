package com.dyenigma.sharing.controller;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.sharing.constant.ErrorConstant;
import com.dyenigma.sharing.constant.SystemConstant;
import com.dyenigma.sharing.service.SysUserService;
import com.dyenigma.sharing.util.JsonUtil;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * backend/com.dyenigma.sharing.controller
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/10 7:50
 */
@RestController
@Api(value = "P2P图书馆测试-登录控制器接口")
@Slf4j
public class LoginController {
    @Autowired
    private SysUserService sysUserService;

    private static final String RESULT = "result";
    private static final String CODE = "code";

    /**
     * @param requestJson
     * @return com.alibaba.fastjson.JSONObject
     * @Description: 登录验证
     * @author dingdongliang
     * @date 2018/4/11 17:57
     */
    @ApiOperation(value = "用户登陆方法", notes = "详细说明文档")
    @PostMapping("/login")
    public JSONObject authLogin(@ApiParam(name = "requestJson",
            value = "格式为{\"username\": \"admin\"," + "\"password\": \"admin\"}", required = true)
                                @RequestBody JSONObject requestJson) {
        JsonUtil.hasAllRequired(requestJson, "username,password");

        String account = requestJson.getString("username");
        String password = requestJson.getString("password");

        JSONObject returnData = new JSONObject();
        returnData.put(CODE, SystemConstant.ERROR_CODE);
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(
                account, password);
        try {
            if (!currentUser.isAuthenticated()) {
                currentUser.login(token);
            }
            log.debug("对用户[{}]进行登录验证..验证通过", account);
            returnData.put(CODE, SystemConstant.SUCCESS_CODE);
            returnData.put(RESULT, SystemConstant.SUCCESS);
        } catch (UnknownAccountException e) {
            log.error(ErrorConstant.NO_ACCOUNT);
            returnData.put(RESULT, ErrorConstant.NO_ACCOUNT);
        } catch (IncorrectCredentialsException e) {
            log.error(ErrorConstant.PWD_ERROR + e.getMessage());
            returnData.put(RESULT, ErrorConstant.PWD_ERROR);
        } catch (LockedAccountException e) {
            log.error(ErrorConstant.ACCOUNT_LOCKED);
            returnData.put(RESULT, ErrorConstant.ACCOUNT_LOCKED);
        } catch (ExcessiveAttemptsException e) {
            log.error(ErrorConstant.TRY_MORE_FIVE);
            returnData.put(RESULT, ErrorConstant.TRY_MORE_FIVE);
        } catch (DisabledAccountException e) {
            log.error(ErrorConstant.ACCOUNT_FORBID);
            returnData.put(RESULT, ErrorConstant.ACCOUNT_FORBID);
        } catch (AuthenticationException e) {
            log.error(ErrorConstant.STH_ERROR + e.getMessage());
            returnData.put(RESULT, ErrorConstant.STH_ERROR);
        }
        return returnData;

    }


    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Description: 登出
     * @author dingdongliang
     * @date 2018/4/11 18:01
     */
    @PostMapping("/logout")
    public JSONObject logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return JsonUtil.successJson();
    }
}

