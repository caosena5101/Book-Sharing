package com.dyenigma.sharing.dao;

import com.dyenigma.sharing.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * backend/com.dyenigma.sharing.dao
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/17 10:35
 */
public interface SysUserMapper {
    /**
     * 根据用户名和密码查询对应的用户，用于登录校验
     *
     * @param account
     * @return com.dyenigma.sharing.entity.SysUser
     * @author dingdongliang
     * @date 2018/4/12 17:43
     */
    SysUser userCertified(@Param("account") String account);

    void insert(SysUser sysUser);
}
