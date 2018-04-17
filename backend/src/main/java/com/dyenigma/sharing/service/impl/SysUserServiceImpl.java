package com.dyenigma.sharing.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.sharing.dao.SysUserMapper;
import com.dyenigma.sharing.entity.SysUser;
import com.dyenigma.sharing.service.SysUserService;
import com.dyenigma.sharing.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * backend/com.dyenigma.sharing.service.impl
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/17 10:33
 */
@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Autowired
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



    @Override
    public void insert(SysUser sysUser) {
        sysUserMapper.insert(sysUser);
    }
}
