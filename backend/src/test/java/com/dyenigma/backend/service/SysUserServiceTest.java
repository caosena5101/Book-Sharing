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
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/13 15:52
 */
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
        BaseDomain.createLog(sysUser, userId);
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
        sysUserService.updateByPrimaryKey(sysUser);
    }

    @Test
    public void deleteByPrimaryKeyTest() {
        sysUserService.deleteByPrimaryKey("9045b033e3ad42b0bf7819a228dd50ee");
    }
}
