package com.dyenigma.sharing.service;

import com.dyenigma.sharing.entity.SysUser;

/**
 * backend/com.dyenigma.sharing.service
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/17 10:28
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 根据用户名和密码查询对应的用户, 用于登录认证
     *
     * @param account
     * @return com.dyenigma.sharing.entity.SysUser
     * @author dingdongliang
     * @date 2018/4/12 17:45
     */
    SysUser userCertified(String account);
}
