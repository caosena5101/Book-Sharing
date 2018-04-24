package com.dyenigma.backend.dao;

import com.dyenigma.backend.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * backend/com.dyenigma.backend.dao
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/17 10:35
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根据用户名和密码查询对应的用户，用于登录校验
     *
     * @param account 用户登录账号，和userName不一样
     * @return com.dyenigma.backend.entity.SysUser
     * @author dingdongliang
     * @date 2018/4/12 17:43
     */
    SysUser userCertified(@Param("account") String account);
}
