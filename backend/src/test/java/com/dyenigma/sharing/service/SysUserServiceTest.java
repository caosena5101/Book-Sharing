package com.dyenigma.sharing.service;

import com.dyenigma.SharingApplicationTests;
import com.dyenigma.sharing.constant.SystemConstant;
import com.dyenigma.sharing.entity.BaseDomain;
import com.dyenigma.sharing.entity.SysUser;
import com.dyenigma.sharing.util.StringUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * backend/com.dyenigma.sharing.service
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/13 15:52
 */
@Slf4j
public class SysUserServiceTest extends SharingApplicationTests {
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
        SysUser sysUser = sysUserService.selectByPrimaryKey("6cc31e7d589b4027b903ceda69718ef7");
        log.info(sysUser.toString());
    }

    @Test
    public void updateByPrimaryKeyTest() {
        SysUser sysUser = new SysUser();
        String roleId = StringUtil.getUUID();
        BaseDomain.createLog(sysUser, roleId);
        sysUserService.updateByPrimaryKey(sysUser);
    }

    @Test
    public void deleteByPrimaryKeyTest() {
        sysUserService.deleteByPrimaryKey("6cc31e7d589b4027b903ceda69718ef7");
    }
}
