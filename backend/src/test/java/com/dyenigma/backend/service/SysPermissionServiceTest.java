package com.dyenigma.backend.service;

import com.dyenigma.backend.BackendApplicationTests;
import com.dyenigma.backend.constant.SystemConstant;
import com.dyenigma.backend.entity.BaseDomain;
import com.dyenigma.backend.entity.SysPermission;
import com.dyenigma.backend.util.StringUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * sharing/com.dyenigma.backend.service
 *
 * @Description : 因为涉及SecurityManager的获取，必须在controller层面测试，这里的所有测试方法作废
 * @Author : dingdongliang
 * @Date : 2018/4/18 16:24
 */
@Deprecated
@Slf4j
public class SysPermissionServiceTest extends BackendApplicationTests {
    @Resource
    private SysPermissionService sysPermissionService;

    @Test
    public void insertTest() {
        SysPermission sysPermission = new SysPermission();
        String pmsnId = StringUtil.getUUID();
        sysPermission.setPmsnId(pmsnId);
        sysPermission.setMenuName("权限管理");
        sysPermission.setMenuCode("sysMgr");
        sysPermission.setPmsnName("");
        sysPermission.setPmsnCode("");
        BaseDomain.createLog(sysPermission);
        sysPermissionService.insert(sysPermission);
    }

    @Test
    public void selectAllTest() {

        List<SysPermission> sysPermissionList = sysPermissionService.selectAll();
        for (SysPermission sysPermission : sysPermissionList) {
            log.info(sysPermission.toString());
        }

    }

    @Test
    public void getUserPmsnTest() {
        List<SysPermission> sysPermissionList = sysPermissionService.getUserPmsn("9045b033e3ad42b0bf7819a228dd50ee");
        for (SysPermission sysPermission : sysPermissionList) {
            log.info(sysPermission.toString());
        }

    }

    @Test
    public void selectByPrimaryKeyTest() {
        SysPermission sysPermission = sysPermissionService.selectByPrimaryKey("e45da745fb2042acba811113be1cbeaf");
        log.info(sysPermission.toString());
    }

    @Test
    public void updateByPrimaryKeyTest() {
        SysPermission sysPermission = sysPermissionService.selectByPrimaryKey("e45da745fb2042acba811113be1cbeaf");
        BaseDomain.updateLog(sysPermission);
        sysPermissionService.updateByPrimaryKey(sysPermission);
    }

    @Test
    public void deleteByPrimaryKeyTest() {
        sysPermissionService.deleteByPrimaryKey("e45da745fb2042acba811113be1cbeaf");
    }

    @Test
    public void selectPageByAllTest() {
        PageInfo<SysPermission> pageInfo = sysPermissionService.selectPageByAll(1, SystemConstant.PAGE_SIZE);
        for (SysPermission sysPermission : pageInfo.getList()) {
            log.info(sysPermission.toString());
        }
    }

}
