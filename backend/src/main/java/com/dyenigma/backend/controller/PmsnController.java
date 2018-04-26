package com.dyenigma.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.backend.entity.BaseDomain;
import com.dyenigma.backend.entity.SysPermission;
import com.dyenigma.backend.exception.GlobalException;
import com.dyenigma.backend.exception.ResponseData;
import com.dyenigma.backend.service.SysPermissionService;
import com.dyenigma.backend.util.JsonUtil;
import com.dyenigma.backend.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * backend/com.dyenigma.backend.controller
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/25 16:38
 */
@RestController
@RequestMapping("/pmsn")
@Api(value = "P2P图书馆-权限相关操作接口")
@Slf4j
public class PmsnController {

    @Resource
    private SysPermissionService sysPermissionService;

    /**
     * 获取所有的权限列表，使用树状展示，还可在角色分配权限时调用
     *
     * @return com.dyenigma.backend.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:08
     */
    @RequiresPermissions(value = {"role:list", "pmsn:list"}, logical = Logical.OR)
    @GetMapping("/getAllPmsn")
    public ResponseData getAllPmsn() {
        List<SysPermission> sysPermissionList = sysPermissionService.selectAll();
        return ResponseData.success(sysPermissionList);
    }

    /**
     * 新增权限
     *
     * @return com.dyenigma.backend.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:09
     */
    @RequiresPermissions("pmsn:add")
    @PostMapping("/addPmsn")
    public ResponseData addPmsn(@ApiParam(name = "requestJson", value = "格式为{\"menuName\":\"菜单管理\"," +
            "\"menuCode\":\"pmsn\",\"pmsnCode\":\"pmsn:list\",\"pmsnName\":\"列表\",\"pmsnType\":\"menu\"," +
            "\"status\":\"E\"}", required = true) @RequestBody JSONObject requestJson) throws GlobalException {
        JsonUtil.hasAllRequired(requestJson, "menuName,menuCode,pmsnCode,pmsnName,pmsnType,status");

        String menuName = requestJson.getString("menuName");
        String menuCode = requestJson.getString("menuCode");
        String pmsnCode = requestJson.getString("pmsnCode");
        String pmsnName = requestJson.getString("pmsnName");
        String pmsnType = requestJson.getString("pmsnType");
        String status = requestJson.getString("status");

        SysPermission sysPermission = new SysPermission();
        String pmsnId = StringUtil.getUUID();
        sysPermission.setPmsnId(pmsnId);
        sysPermission.setMenuCode(menuCode);
        sysPermission.setMenuName(menuName);
        sysPermission.setPmsnCode(pmsnCode);
        sysPermission.setPmsnName(pmsnName);
        sysPermission.setPmsnType(pmsnType);
        sysPermission.setStatus(status);
        BaseDomain.createLog(sysPermission);

        sysPermissionService.insert(sysPermission);

        return ResponseData.success();
    }

    /**
     * 修改权限
     *
     * @return com.dyenigma.backend.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:09
     */
    @RequiresPermissions("pmsn:update")
    @PostMapping("/updatePmsn")
    public ResponseData updatePmsn(@ApiParam(name = "requestJson", value = "格式为{\"menuName\":\"菜单管理\"," +
            "\"menuCode\":\"pmsn\",\"pmsnCode\":\"pmsn:list\",\"pmsnName\":\"列表\",\"pmsnType\":\"menu\"," +
            "\"status\":\"E\",\"pmsnId\":\"c4517de81fac423bb782baa4e48d311b\"}", required = true)
                                   @RequestBody JSONObject requestJson) throws GlobalException {
        JsonUtil.hasAllRequired(requestJson, "pmsnId");

        String pmsnId = requestJson.getString("pmsnId");
        String menuName = requestJson.getString("menuName");
        String menuCode = requestJson.getString("menuCode");
        String pmsnCode = requestJson.getString("pmsnCode");
        String pmsnName = requestJson.getString("pmsnName");
        String pmsnType = requestJson.getString("pmsnType");
        String status = requestJson.getString("status");

        SysPermission sysPermission = sysPermissionService.selectByPrimaryKey(pmsnId);

        //判断是否为空，然后进行更新或者直接返回
        if (sysPermission != null) {

            if (menuCode != null) {
                sysPermission.setMenuCode(menuCode);
            }
            if (menuName != null) {
                sysPermission.setMenuName(menuName);
            }
            if (pmsnCode != null) {
                sysPermission.setPmsnCode(pmsnCode);
            }
            if (pmsnName != null) {
                sysPermission.setPmsnName(pmsnName);
            }
            if (pmsnType != null) {
                sysPermission.setPmsnType(pmsnType);
            }
            if (status != null) {
                sysPermission.setStatus(status);
            }

            BaseDomain.updateLog(sysPermission);

            sysPermissionService.updateByPrimaryKey(sysPermission);

            return ResponseData.success();
        } else {
            return ResponseData.error();
        }
    }

    /**
     * 删除权限，需要同时删除角色-权限对应表中的该权限记录
     *
     * @return com.dyenigma.backend.exception.ResponseData
     * @author dingdongliang
     * @date 2018/4/18 16:09
     */
    @RequiresPermissions("pmsn:delete")
    @DeleteMapping("/deletePmsn")
    public ResponseData deletePmsn(@RequestBody JSONObject requestJson) throws GlobalException {
        JsonUtil.hasAllRequired(requestJson, "pmsnId");
        String pmsnId = requestJson.getString("pmsnId");
        sysPermissionService.delete(pmsnId);
        return ResponseData.success();
    }

}
