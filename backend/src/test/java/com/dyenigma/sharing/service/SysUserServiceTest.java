package com.dyenigma.sharing.service;

import com.dyenigma.SharingApplicationTests;
import com.dyenigma.sharing.entity.BaseDomain;
import com.dyenigma.sharing.entity.SysUser;
import com.dyenigma.sharing.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;

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
}
