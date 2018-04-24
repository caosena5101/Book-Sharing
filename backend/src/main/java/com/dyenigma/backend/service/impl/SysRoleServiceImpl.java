package com.dyenigma.backend.service.impl;

import com.dyenigma.backend.dao.SysUserMapper;
import com.dyenigma.backend.dao.SysUserRoleMapper;
import com.dyenigma.backend.entity.SysRole;
import com.dyenigma.backend.service.SysRoleService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * sharing/com.dyenigma.backend.service.impl
 *
 * @Description : 角色操作类，包含角色的CURD、角色的权限分配等
 * @Author : dingdongliang
 * @Date : 2018/4/18 15:20
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {
    @Resource
    private SysUserMapper sysUserMapper;



}
