package com.dyenigma.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.backend.constant.SystemConstant;
import com.dyenigma.backend.entity.BaseDomain;
import com.dyenigma.backend.entity.SysRole;
import com.dyenigma.backend.exception.GlobalException;
import com.dyenigma.backend.exception.ResponseData;
import com.dyenigma.backend.service.SysPermissionService;
import com.dyenigma.backend.service.SysRoleService;
import com.dyenigma.backend.util.JsonUtil;
import com.dyenigma.backend.util.StringUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * sharing/com.dyenigma.backend.controller
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
     * 获取所有的角色列表，用于分页展示角色
     *
     * @return com.dyenigma.backend.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:08
     */
    @RequiresPermissions("role:list")
    @GetMapping("/roleList")
    public ResponseData roleList(@ApiParam(name = "requestJson", value = "格式为{\"pageNo\":\"1\"}", required = true)
                                 @RequestBody JSONObject requestJson) throws GlobalException {
        JsonUtil.hasAllRequired(requestJson, "pageNo");
        int pageNo = Integer.parseInt(requestJson.getString("pageNo"));
        PageInfo<SysRole> sysRolePageInfo = sysRoleService.selectPageByAll(pageNo, SystemConstant.PAGE_SIZE);
        return ResponseData.success(sysRolePageInfo);
    }

    /**
     * 新增角色
     *
     * @return com.dyenigma.backend.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:09
     */
    @RequiresPermissions("role:add")
    @PostMapping("/addRole")
    public ResponseData addRole(@ApiParam(name = "requestJson",
            value = "格式为{\"roleName\":\"监督者\",\"permissions\":" +
                    "\"2e0b4be914de494d99236f7d5141804a|6b12817ab5b943e1b4d4218617dd3ca3\"}", required = true)
                                @RequestBody JSONObject requestJson) throws GlobalException {
        JsonUtil.hasAllRequired(requestJson, "roleName,permissions");

        String roleName = requestJson.getString("roleName");
        String permissions = requestJson.getString("permissions");

        String[] pmsnIds = permissions.split("\\|");

        SysRole sysRole = new SysRole();
        String roleId = StringUtil.getUUID();
        sysRole.setRoleId(roleId);
        sysRole.setRoleName(roleName);

        BaseDomain.createLog(sysRole);

        sysRoleService.insert(sysRole, pmsnIds);

        return ResponseData.success();
    }

    /**
     * 修改角色
     *
     * @return com.dyenigma.backend.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:09
     */
    @RequiresPermissions("role:update")
    @PostMapping("/updateRole")
    public ResponseData updateRole(@ApiParam(name = "requestJson", value = "格式为{\"roleId\":" +
            "\"8cefc3f9409348bb9677118aed62fdfb\",\"roleName\":\"破坏者\",\"permissions\":" +
            "\"2e0b4be914de494d99236f7d5141804a|6b12817ab5b943e1b4d4218617dd3ca3\",\"status\":\"E\"}",
            required = true) @RequestBody JSONObject requestJson) throws GlobalException {
        JsonUtil.hasAllRequired(requestJson, "roleId,roleName,permissions");

        String roleId = requestJson.getString("roleId");
        String roleName = requestJson.getString("roleName");
        String pmsnStr = requestJson.getString("permissions");
        String status = requestJson.getString("status");
        String[] pmsnIds = pmsnStr.split("\\|");

        SysRole sysRole = sysRoleService.selectByPrimaryKey(roleId);
        sysRole.setRoleName(roleName);
        sysRole.setStatus(status);

        sysRoleService.update(sysRole, pmsnIds);

        return ResponseData.success();

    }

    /**
     * 删除角色
     *
     * @return com.dyenigma.backend.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:09
     */
    @RequiresPermissions("role:delete")
    @DeleteMapping("/deleteRole")
    public ResponseData deleteRole(@RequestBody JSONObject requestJson) throws GlobalException {
        JsonUtil.hasAllRequired(requestJson, "roleId");
        String roleId = requestJson.getString("roleId");
        sysRoleService.delete(roleId);
        return ResponseData.success();
    }
}
