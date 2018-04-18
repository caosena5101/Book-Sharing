package com.dyenigma.sharing.controller;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.sharing.entity.SysRole;
import com.dyenigma.sharing.entity.SysUser;
import com.dyenigma.sharing.exception.GlobalException;
import com.dyenigma.sharing.exception.ResponseData;
import com.dyenigma.sharing.service.SysRoleService;
import com.dyenigma.sharing.service.SysUserService;
import com.dyenigma.sharing.util.JsonUtil;
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
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * sharing/com.dyenigma.sharing.controller
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
     * @param request
     * @return com.dyenigma.sharing.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:10
     */
    @RequiresPermissions("user:list")
    @GetMapping("/userList")
    public ResponseData userList(HttpServletRequest request) throws GlobalException {
        List<SysUser> userList = sysUserService.selectAll();
        return ResponseData.success(userList);
    }

    /**
     * 添加用户
     *
     * @param requestJson
     * @return com.dyenigma.sharing.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:11
     */
    @RequiresPermissions("user:add")
    @PostMapping("/addUser")
    public ResponseData addUser(@RequestBody JSONObject requestJson) throws GlobalException {
        JsonUtil.hasAllRequired(requestJson, "username, password, nickname,   roleId");
        SysUser sysUser = new SysUser();
        //TODO 实例化用户
        sysUserService.insert(sysUser);
        return ResponseData.success();
    }

    /**
     * 更新用户
     *
     * @param requestJson
     * @return com.dyenigma.sharing.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:11
     */
    @RequiresPermissions("user:update")
    @PostMapping("/updateUser")
    public ResponseData updateUser(@RequestBody JSONObject requestJson) throws GlobalException {
        JsonUtil.hasAllRequired(requestJson, " nickname,   roleId, deleteStatus, userId");

        SysUser sysUser = sysUserService.selectByPrimaryKey("");

        sysUser.setIsOnline(1);
        sysUserService.updateByPrimaryKey(sysUser);
        return ResponseData.success();
    }

    /**
     * 获取所有的角色信息，用于给用户分配角色
     *
     * @return com.dyenigma.sharing.exception.ResponseData
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
