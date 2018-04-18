package com.dyenigma.sharing.service.impl;

import com.dyenigma.sharing.dao.SysPermissionMapper;
import com.dyenigma.sharing.entity.SysPermission;
import com.dyenigma.sharing.service.SysPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * sharing/com.dyenigma.sharing.service.impl
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/18 15:20
 */
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission> implements SysPermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 根据用户ID查询其所拥有的所有权限
     *
     * @param userId
     * @return java.util.List<com.dyenigma.sharing.entity.SysPermission>
     * @author dingdongliang
     * @date 2018/4/18 15:44
     */
    @Override
    public List<SysPermission> getUserPermission(String userId) {
        return sysPermissionMapper.getUserPermission(userId);
    }
}
