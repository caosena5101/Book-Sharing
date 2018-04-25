package com.dyenigma.backend.service.impl;

import com.dyenigma.backend.constant.SystemConstant;
import com.dyenigma.backend.dao.SysPermissionMapper;
import com.dyenigma.backend.entity.SysPermission;
import com.dyenigma.backend.service.SysPermissionService;
import com.dyenigma.backend.util.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * sharing/com.dyenigma.backend.service.impl
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/18 15:20
 */
@Transactional(rollbackFor = Exception.class)
@Service
@Slf4j
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission> implements SysPermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 根据用户ID查询其所拥有的所有权限
     *
     * @param userId 用户ID
     * @return java.util.List<com.dyenigma.backend.entity.SysPermission>
     * @author dingdongliang
     * @date 2018/4/18 15:44
     */
    @Override
    public List<SysPermission> getUserPermission(String userId) {
        return sysPermissionMapper.getUserPermission(userId);
    }

    /**
     * 查询当前登录用户的权限等信息
     *
     * @return java.util.List<com.dyenigma.backend.entity.SysPermission>
     * @author dingdongliang
     * @date 2018/4/23 14:50
     */
    @Override
    public List<SysPermission> getCurrentInfo() {
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();

        String userId = ShiroUtil.getUserId();

        List<SysPermission> userPermission = getUserPermission(userId);
        session.setAttribute(SystemConstant.SESSION_USER_PERMISSION, userPermission);
        return userPermission;
    }
}
