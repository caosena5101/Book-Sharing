package com.dyenigma.sharing.service;

import com.dyenigma.SharingApplicationTests;
import com.dyenigma.sharing.entity.SysUser;
import com.dyenigma.sharing.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * backend/com.dyenigma.sharing.service
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/13 15:52
 */
@Slf4j
public class SysUserServiceTest extends SharingApplicationTests {
    @Autowired
    private SysUserService sysUserService;

    @Test
    public void insertTest() {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(StringUtil.getUUID());
        sysUser.setAccount("admin");
        sysUser.setPassword(StringUtil.encryptPassword("admin", "admin"));
        sysUser.setStatus("E");
        sysUserService.insert(sysUser);
    }
}
