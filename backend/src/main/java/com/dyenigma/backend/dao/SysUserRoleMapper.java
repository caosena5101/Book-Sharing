package com.dyenigma.backend.dao;

import com.dyenigma.backend.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * backend/com.dyenigma.backend.dao
 *
 * @Description : 用户-角色操作类
 * @Author : dingdongliang
 * @Date : 2018/4/17 10:35
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 批量插入用户-角色的对应关系，该方法的SQL语句中，包含Mybatis调用java类方法用法和循环操作
     *
     * @param userId  用户ID
     * @param roleIds 权限集合
     * @return void
     * @author dingdongliang
     * @date 2018/4/24 11:49
     */
    void insertMany(@Param("userId") String userId, @Param("roleIds") List<String> roleIds);

    /**
     * 根据用户Id和角色Id查询用户角色映射对象，用来更新状态
     *
     * @param userId 用户ID
     * @param roleId 权限集合
     * @return com.dyenigma.backend.entity.SysUserRole
     * @author dingdongliang
     * @date 2018/4/24 10:08
     */
    SysUserRole selectByDoubleId(@Param("userId") String userId, @Param("roleId") String roleId);

    /**
     * 根据用户ID，查询用户的多个角色，一对多的关系，单独写一个方法
     *
     * @param userId 用户ID
     * @return java.util.List<com.dyenigma.backend.entity.SysUserRole>
     * @author dingdongliang
     * @date 2018/4/24 11:50
     */
    List<SysUserRole> selectByUserId(@Param("userId") String userId);

}