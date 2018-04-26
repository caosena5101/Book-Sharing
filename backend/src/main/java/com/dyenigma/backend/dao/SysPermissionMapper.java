package com.dyenigma.backend.dao;

import com.dyenigma.backend.entity.SysPermission;

import java.util.List;

/**
 * sharing/com.dyenigma.backend.dao
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/18 15:42
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    /**
     * 根据用户ID查询其所拥有的所有权限
     *
     * @param userId 用户ID
     * @return java.util.List<com.dyenigma.backend.entity.SysPermission>
     * @author dingdongliang
     * @date 2018/4/18 15:44
     */
    List<SysPermission> getUserPmsn(String userId);
}
