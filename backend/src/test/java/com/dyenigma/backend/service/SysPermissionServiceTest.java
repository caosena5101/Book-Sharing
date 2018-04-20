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
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/18 16:24
 */
@Slf4j
public class SysPermissionServiceTest extends BackendApplicationTests {
    @Resource
    private SysPermissionService sysPermissionService;

    @Test
    public void insertTest() {
        SysPermission sysPermission = new SysPermission();
        String pmsnId = StringUtil.getUUID();
        BaseDomain.createLog(sysPermission, pmsnId);
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
    public void getUserPermissionTest() {
        List<SysPermission> sysPermissionList = sysPermissionService.getUserPermission("");
        for (SysPermission sysPermission : sysPermissionList) {
            log.info(sysPermission.toString());
        }

    }

    @Test
    public void selectByPrimaryKeyTest() {
        SysPermission sysPermission = sysPermissionService.selectByPrimaryKey("");
        log.info(sysPermission.toString());
    }

    @Test
    public void updateByPrimaryKeyTest() {
        SysPermission sysPermission = new SysPermission();
        String pmsnId = StringUtil.getUUID();
        BaseDomain.createLog(sysPermission, pmsnId);
        sysPermissionService.updateByPrimaryKey(sysPermission);
    }

    @Test
    public void deleteByPrimaryKeyTest() {
        sysPermissionService.deleteByPrimaryKey("");
    }

    @Test
    public void selectPageByAllTest() {
        PageInfo<SysPermission> pageInfo = sysPermissionService.selectPageByAll(1, SystemConstant.PAGE_SIZE);
        for (SysPermission sysPermission : pageInfo.getList()) {
            log.info(sysPermission.toString());
        }
    }
}
