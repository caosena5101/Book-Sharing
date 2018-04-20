package com.dyenigma.backend.service;

import com.dyenigma.backend.entity.SysUser;

/**
 * backend/com.dyenigma.backend.service
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
     * @return com.dyenigma.backend.entity.SysUser
     * @author dingdongliang
     * @date 2018/4/12 17:45
     */
    SysUser userCertified(String account);
}
