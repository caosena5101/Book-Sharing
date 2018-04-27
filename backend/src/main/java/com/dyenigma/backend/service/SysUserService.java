package com.dyenigma.backend.service;

import com.dyenigma.backend.entity.SysUser;

/**
 * backend/com.dyenigma.backend.service
 *
 * @Description : 用户操作类，包含用户的CURD和用户的角色分配等
 * @Author : dingdongliang
 * @Date : 2018/4/17 10:28
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 根据用户名和密码查询对应的用户, 用于登录认证
     *
     * @param account 账号
     * @return com.dyenigma.backend.entity.SysUser
     * @author dingdongliang
     * @date 2018/4/12 17:45
     */
    SysUser userCertified(String account);

    /**
     * 给用户赋予角色，角色可以为多个
     *
     * @param userId  用户主键
     * @param roleIds 角色ID数组
     * @return void
     * @author dingdongliang
     * @date 2018/4/24 8:33
     */
    void putRoleToUser(String userId, String[] roleIds);

    /**
     * 更新用户的角色
     *
     * @param userId  用户主键
     * @param roleIds 角色ID集合
     * @return void
     * @author dingdongliang
     * @date 2018/4/24 9:31
     */
    void updateRoleToUser(String userId, String[] roleIds);

    /**
     * 新增用户，同时添加角色
     *
     * @param sysUser 用户
     * @param roleIds 角色ID集合
     * @return void
     * @author dingdongliang
     * @date 2018/4/24 11:13
     */
    void insert(SysUser sysUser, String[] roleIds);

    /**
     * 修改用户，同时修改用户-角色映射
     *
     * @param sysUser 用户
     * @param roleIds 角色ID集合
     * @return void
     * @author dingdongliang
     * @date 2018/4/24 11:13
     */
    void update(SysUser sysUser, String[] roleIds);

}
