package com.dyenigma.backend.service;

import com.dyenigma.backend.entity.SysPermission;

import java.util.List;

/**
 * sharing/com.dyenigma.backend.service
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/18 15:11
 */
public interface SysPermissionService extends BaseService<SysPermission> {
    /**
     * 根据用户ID查询其所拥有的所有权限
     *
     * @param userId
     * @return java.util.List<com.dyenigma.backend.entity.SysPermission>
     * @author dingdongliang
     * @date 2018/4/18 15:44
     */
    List<SysPermission> getUserPermission(String userId);

    /**
     * 查询当前登录用户的权限等信息
     *
     * @return java.util.List<com.dyenigma.backend.entity.SysPermission>
     * @author dingdongliang
     * @date 2018/4/23 14:50
     */
    List<SysPermission> getCurrentInfo();
}
