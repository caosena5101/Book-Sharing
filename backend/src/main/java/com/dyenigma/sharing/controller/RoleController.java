package com.dyenigma.sharing.controller;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.sharing.entity.SysPermission;
import com.dyenigma.sharing.entity.SysRole;
import com.dyenigma.sharing.exception.GlobalException;
import com.dyenigma.sharing.exception.ResponseData;
import com.dyenigma.sharing.service.SysPermissionService;
import com.dyenigma.sharing.service.SysRoleService;
import com.dyenigma.sharing.util.JsonUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * sharing/com.dyenigma.sharing.controller
 *
 * @Description : 权限相关API
 * @Author : dingdongliang
 * @Date : 2018/4/18 15:07
 */
@RestController
@RequestMapping("/role")
@Api(value = "P2P图书馆-角色相关操作接口")
@Slf4j
public class RoleController {

    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysPermissionService sysPermissionService;

    /**
     * 获取所有的角色列表，用于展示角色或者给用户分配角色时使用
     *
     * @return com.dyenigma.sharing.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:08
     */
    @RequiresPermissions("role:list")
    @GetMapping("/roleList")
    public ResponseData roleList() {
        List<SysRole> roleList = sysRoleService.selectAll();
        return ResponseData.success(roleList);
    }

    /**
     * 查询所有权限, 给角色分配权限时调用
     *
     * @return com.dyenigma.sharing.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:09
     */
    @RequiresPermissions("role:list")
    @GetMapping("/allPermission")
    public ResponseData allPermission() {

        List<SysPermission> permissionList = sysPermissionService.selectAll();
        return ResponseData.success(permissionList);
    }

    /**
     * 新增角色
     *
     * @param requestJson
     * @return com.dyenigma.sharing.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:09
     */
    @RequiresPermissions("role:add")
    @PostMapping("/addRole")
    public ResponseData addRole(@RequestBody JSONObject requestJson) throws GlobalException {
        JsonUtil.hasAllRequired(requestJson, "roleName,permissions");
        SysRole sysRole = new SysRole();
        sysRoleService.insert(sysRole);
        return ResponseData.success();
    }

    /**
     * 修改角色
     *
     * @param requestJson
     * @return com.dyenigma.sharing.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:09
     */
    @RequiresPermissions("role:update")
    @PostMapping("/updateRole")
    public ResponseData updateRole(@RequestBody JSONObject requestJson) throws GlobalException {
        JsonUtil.hasAllRequired(requestJson, "roleId,roleName,permissions");
        SysRole sysRole = sysRoleService.selectByPrimaryKey("");
        sysRole.setRoleDesc("");
        sysRoleService.updateByPrimaryKey(sysRole);
        return ResponseData.success();
    }

    /**
     * 删除角色
     *
     * @param requestJson
     * @return com.dyenigma.sharing.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:09
     */
    @RequiresPermissions("role:delete")
    @PostMapping("/deleteRole")
    public ResponseData deleteRole(@RequestBody JSONObject requestJson) throws GlobalException {
        JsonUtil.hasAllRequired(requestJson, "roleId");
        sysRoleService.deleteByPrimaryKey("");
        return ResponseData.success();
    }
}
