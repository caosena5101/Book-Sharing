package com.dyenigma.sharing.service.impl;

import com.dyenigma.sharing.dao.SysUserMapper;
import com.dyenigma.sharing.entity.SysUser;
import com.dyenigma.sharing.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * backend/com.dyenigma.sharing.service.impl
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/17 10:33
 */
@Service
@Slf4j
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 根据用户名和密码查询对应的用户, 用于登录认证
     *
     * @param account
     * @return com.dyenigma.sharing.entity.SysUser
     * @author dingdongliang
     * @date 2018/4/12 17:45
     */
    @Override
    public SysUser userCertified(String account) {
        return sysUserMapper.userCertified(account);
    }


}
