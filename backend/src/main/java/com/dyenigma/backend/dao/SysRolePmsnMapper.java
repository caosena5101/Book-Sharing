package com.dyenigma.backend.dao;

import com.dyenigma.backend.entity.SysRolePmsn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * backend/com.dyenigma.backend.dao
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/25 11:17
 */
public interface SysRolePmsnMapper extends BaseMapper<SysRolePmsn> {

    /**
     * 批量插入角色-权限先的对应关系，该方法的SQL语句中，包含Mybatis调用java类方法用法和循环操作
     *
     * @param roleId  角色ID
     * @param pmsnIds 权限集合
     * @param userId  操作用户
     * @return void
     * @author dingdongliang
     * @date 2018/4/25 11:36
     */
    void insertMany(@Param("roleId") String roleId, @Param("pmsnIds") List<String> pmsnIds, @Param("userId") String
            userId);

    /**
     * 根据角色ID，查询角色的多个权限，一对多的关系，单独写一个方法
     *
     * @param roleId 角色ID
     * @return java.util.List<com.dyenigma.backend.entity.SysRolePmsn>
     * @author dingdongliang
     * @date 2018/4/24 11:50
     */
    List<SysRolePmsn> selectByRoleId(@Param("roleId") String roleId);

    /**
     * 根据角色ID，更新角色对应权限的状态，一对多的关系，单独写一个方法
     *
     * @param roleId 角色ID
     * @return void
     * @author dingdongliang
     * @date 2018/4/25 16:26
     */
    void deleteByRoleId(@Param("roleId") String roleId);

    /**
     * 根据用户Id和角色Id查询用户角色映射对象，用来更新状态
     *
     * @param roleId 角色ID
     * @param pmsnId 权限ID
     * @return com.dyenigma.backend.entity.SysRolePmsn
     * @author dingdongliang
     * @date 2018/4/24 10:08
     */
    SysRolePmsn selectByDoubleId(@Param("roleId") String roleId, @Param("pmsnId") String pmsnId);
}
