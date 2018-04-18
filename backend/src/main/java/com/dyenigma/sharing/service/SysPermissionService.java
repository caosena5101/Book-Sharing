package com.dyenigma.sharing.service;

import com.dyenigma.sharing.entity.SysPermission;

import java.util.List;

/**
 * sharing/com.dyenigma.sharing.service
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
     * @return java.util.List<com.dyenigma.sharing.entity.SysPermission>
     * @author dingdongliang
     * @date 2018/4/18 15:44
     */
    List<SysPermission> getUserPermission(String userId);
}
