package com.dyenigma.backend.service;

import com.dyenigma.backend.BackendApplicationTests;
import com.dyenigma.backend.constant.SystemConstant;
import com.dyenigma.backend.entity.BaseDomain;
import com.dyenigma.backend.entity.SysRole;
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
 * @Date : 2018/4/18 16:19
 */
@Deprecated
@Slf4j
public class SysRoleServiceTest extends BackendApplicationTests {
    @Resource
    private SysRoleService sysRoleService;

    @Test
    public void insertTest() {
        SysRole sysRole = new SysRole();
        String roleId = StringUtil.getUUID();
        sysRole.setRoleId(roleId);
        sysRole.setRoleName("管理员");
        sysRole.setStatus("E");
        BaseDomain.createLog(sysRole);
        sysRoleService.insert(sysRole);
    }

    @Test
    public void selectAllTest() {

        List<SysRole> sysRoleList = sysRoleService.selectAll();
        for (SysRole sysRole : sysRoleList) {
            log.info(sysRole.toString());
        }

    }

    @Test
    public void selectByPrimaryKeyTest() {
        SysRole sysRole = sysRoleService.selectByPrimaryKey("64205b16f5d04b47aea4b091d88c243e");
        log.info(sysRole.toString());
    }

    @Test
    public void updateByPrimaryKeyTest() {
        SysRole sysRole = sysRoleService.selectByPrimaryKey("64205b16f5d04b47aea4b091d88c243e");
        sysRole.setRoleName("反叛者");
        BaseDomain.updateLog(sysRole);
        sysRoleService.updateByPrimaryKey(sysRole);
    }

    @Test
    public void deleteByPrimaryKeyTest() {
        sysRoleService.deleteByPrimaryKey("64205b16f5d04b47aea4b091d88c243e");
    }

    @Test
    public void selectPageByAllTest() {
        PageInfo<SysRole> pageInfo = sysRoleService.selectPageByAll(1, SystemConstant.PAGE_SIZE);
        for (SysRole sysRole : pageInfo.getList()) {
            log.info(sysRole.toString());
        }
    }
}
