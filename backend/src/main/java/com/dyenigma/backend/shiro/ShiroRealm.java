package com.dyenigma.backend.shiro;

import com.dyenigma.backend.constant.RespCodeEnum;
import com.dyenigma.backend.constant.SystemConstant;
import com.dyenigma.backend.entity.SysPermission;
import com.dyenigma.backend.entity.SysUser;
import com.dyenigma.backend.service.SysPermissionService;
import com.dyenigma.backend.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * backend/com.dyenigma.backend.shiro
 *
 * @Description : 使用自定义realm进行授权，需要继承AuthorizingRealm
 * @Author : dingdongliang
 * @Date : 2018/4/3 8:30
 */
@Slf4j
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysPermissionService sysPermissionService;

    public ShiroRealm() {
        super();
    }

    /**
     * 获取授权信息，首先检查Session中的信息，如果为空再重新获取
     *
     * @param principals 主信息
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @author dingdongliang
     * @date 2018/4/18 14:39
     */
    @SuppressWarnings("unchecked")
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        Session session = SecurityUtils.getSubject().getSession();

        List<SysPermission> sysPermissionList = (List<SysPermission>) session.getAttribute(SystemConstant
                .SESSION_USER_PERMISSION);

        if (sysPermissionList == null) {
            sysPermissionList = sysPermissionService.getCurrentPmsn((SysUser) principals.getPrimaryPrincipal());
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<>();

        for (SysPermission sysPermission : sysPermissionList) {
            permsSet.add(sysPermission.getPmsnCode());
        }

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(permsSet);
        return authorizationInfo;

    }

    /**
     * 获取认证信息, 验证当前登录的Subject, 登录控制器login()方法执行Subject.login()时调用此方法
     *
     * @param authcToken 检验信息
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @author dingdongliang
     * @date 2018/4/18 14:39
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {

        UsernamePasswordToken upToken = (UsernamePasswordToken) authcToken;

        String account = upToken.getUsername();

        SysUser sysUser = sysUserService.userCertified(account);

        if (sysUser == null) {
            throw new UnknownAccountException(RespCodeEnum.NO_ACCOUNT.getMessage());
        }

        if (SystemConstant.INVALID.equals(sysUser.getStatus())) {
            throw new LockedAccountException(RespCodeEnum.ACCOUNT_LOCKED.getMessage());
        }

        ByteSource salt = ByteSource.Util.bytes(account);

        //第一个参数是要验证的账号，第二个参数是数据库中获取的密码，第三个参数是盐值
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                sysUser, sysUser.getPassword(), salt, getName());

        sysUser.setPassword("");
        SecurityUtils.getSubject().getSession().setAttribute(SystemConstant.SESSION_USER_INFO, sysUser);
        return authenticationInfo;
    }

    /**
     * 用户退出时，清空所有的用户缓存
     *
     * @param principals 主信息
     * @return void
     * @author dingdongliang
     * @date 2018/4/17 17:50
     */
    @Override
    public void onLogout(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
        clearAllCachedAuthorizationInfo();
    }

    /**
     * 更新用户授权信息缓存.
     *
     * @param principal 主信息
     * @return void
     * @author dingdongliang
     * @date 2018/4/17 17:45
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     *
     * @return void
     * @author dingdongliang
     * @date 2018/4/17 17:46
     */
    private void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
}

