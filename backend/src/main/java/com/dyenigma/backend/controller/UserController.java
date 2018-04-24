package com.dyenigma.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.backend.entity.BaseDomain;
import com.dyenigma.backend.entity.SysRole;
import com.dyenigma.backend.entity.SysUser;
import com.dyenigma.backend.exception.GlobalException;
import com.dyenigma.backend.exception.ResponseData;
import com.dyenigma.backend.service.SysRoleService;
import com.dyenigma.backend.service.SysUserService;
import com.dyenigma.backend.util.JsonUtil;
import com.dyenigma.backend.util.StringUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * sharing/com.dyenigma.backend.controller
 *
 * @Description : 用户相关API
 * @Author : dingdongliang
 * @Date : 2018/4/18 14:21
 */
@RestController
@RequestMapping("/user")
@Api(value = "P2P图书馆-用户相关操作接口")
@Slf4j
public class UserController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 查询用户列表
     *
     * @return com.dyenigma.backend.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:10
     */
    @RequiresPermissions("user:list")
    @GetMapping("/userList")
    public ResponseData userList() {
        List<SysUser> userList = sysUserService.selectAll();
        return ResponseData.success(userList);
    }

    /**
     * 添加用户,同时分配角色,注意必须有默认角色
     *
     * @param requestJson 请求的格式为JSONObject
     * @return com.dyenigma.backend.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:11
     */
    @RequiresPermissions("user:add")
    @PostMapping("/addUser")
    public ResponseData addUser(@RequestBody JSONObject requestJson) throws GlobalException {
        JsonUtil.hasAllRequired(requestJson, "userName,password,realName,roleId");

        String account = requestJson.getString("userName");
        String password = requestJson.getString("password");
        String userName = requestJson.getString("realName");

        String roleStr = requestJson.getString("roleId");
        String[] roleIds = roleStr.split("\\|");

        SysUser sysUser = new SysUser();
        String userId = StringUtil.getUUID();
        sysUser.setUserId(userId);
        sysUser.setUserName(userName);
        sysUser.setAccount(account);
        sysUser.setPassword(StringUtil.encryptPassword(password, account));

        BaseDomain.createLog(sysUser);

        sysUserService.insert(sysUser, roleIds);

        return ResponseData.success();
    }

    /**
     * 更新用户
     *
     * @return com.dyenigma.backend.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:11
     */
    @RequiresPermissions("user:update")
    @PostMapping("/updateUser")
    public ResponseData updateUser(@RequestBody JSONObject requestJson) throws GlobalException {
        JsonUtil.hasAllRequired(requestJson, "realName,roleId,status,userId");

        String userId = requestJson.getString("userId");
        String userName = requestJson.getString("realName");
        String roleStr = requestJson.getString("roleId");
        String status = requestJson.getString("status");
        String[] roleIds = roleStr.split("\\|");

        SysUser sysUser = sysUserService.selectByPrimaryKey(userId);
        sysUser.setUserName(userName);
        sysUser.setStatus(status);

        sysUserService.update(sysUser, roleIds);

        return ResponseData.success();
    }

    /**
     * 获取所有的角色信息，用于给用户分配角色
     *
     * @return com.dyenigma.backend.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:12
     */
    @RequiresPermissions(value = {"user:add", "user:update"}, logical = Logical.OR)
    @GetMapping("/getAllRoles")
    public ResponseData getAllRoles() {
        List<SysRole> list = sysRoleService.selectAll();
        return ResponseData.success(list);
    }
}
