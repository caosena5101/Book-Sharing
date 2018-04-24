package com.dyenigma.backend.service;

import com.dyenigma.backend.BackendApplicationTests;
import com.dyenigma.backend.constant.SystemConstant;
import com.dyenigma.backend.entity.BaseDomain;
import com.dyenigma.backend.entity.SysUser;
import com.dyenigma.backend.util.StringUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * backend/com.dyenigma.backend.service
 *
 * @Description : 因为涉及SecurityManager的获取，必须在controller层面测试，这里的所有测试方法作废
 * @Author : dingdongliang
 * @Date : 2018/4/13 15:52
 */
@Deprecated
@Slf4j
public class SysUserServiceTest extends BackendApplicationTests {
    @Resource
    private SysUserService sysUserService;

    @Test
    public void insertTest() {
        SysUser sysUser = new SysUser();
        String userId = StringUtil.getUUID();
        sysUser.setUserId(userId);
        sysUser.setAccount("dyenigma");
        sysUser.setPassword(StringUtil.encryptPassword("admin", "dyenigma"));
        sysUser.setStatus("E");
        BaseDomain.createLog(sysUser);
        sysUserService.insert(sysUser);
    }

    @Test
    public void selectAllTest() {

        List<SysUser> sysUserList = sysUserService.selectAll();
        for (SysUser sysUser : sysUserList) {
            log.info(sysUser.toString());
        }

    }

    @Test
    public void selectPageByAllTest() {
        PageInfo<SysUser> pageInfo = sysUserService.selectPageByAll(1, SystemConstant.PAGE_SIZE);
        for (SysUser sysUser : pageInfo.getList()) {
            log.info(sysUser.toString());
        }
    }

    @Test
    public void selectByPrimaryKeyTest() {
        SysUser sysUser = sysUserService.selectByPrimaryKey("9045b033e3ad42b0bf7819a228dd50ee");
        log.info(sysUser == null ? "" : sysUser.toString());
    }

    @Test
    public void updateByPrimaryKeyTest() {
        SysUser sysUser = sysUserService.selectByPrimaryKey("9045b033e3ad42b0bf7819a228dd50ee");
        sysUser.setUserName("super man");
        BaseDomain.updateLog(sysUser);
        sysUserService.updateByPrimaryKey(sysUser);
    }

    @Test
    public void deleteByPrimaryKeyTest() {
        sysUserService.deleteByPrimaryKey("9045b033e3ad42b0bf7819a228dd50ee");
    }

    @Test
    public void putRoleToUserTest() {

        String userId = "9045b033e3ad42b0bf7819a228dd50ee";
        String[] roleIds = {"64205b16f5d04b47aea4b091d88c243e", "8cefc3f9409348bb9677118aed62fdfb", "fb7f035401204cff8c58f240b866c925"};

        sysUserService.putRoleToUser(userId, roleIds);
    }

    @Test
    public void updateRoleToUserTest() {
        String userId = "9045b033e3ad42b0bf7819a228dd50ee";
        String[] roleIds = {"64205b16f5d04b47aea4b091d88c243e", "fb7f035401204cff8c58f240b866c925"};

        sysUserService.updateRoleToUser(userId, roleIds);
    }
}

