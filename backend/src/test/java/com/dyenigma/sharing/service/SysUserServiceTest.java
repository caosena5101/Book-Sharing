package com.dyenigma.sharing.service;

import com.dyenigma.SharingApplicationTests;
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
    public void authLoginTest() {
        String account = "dyenigma";
        String password = "admin";
        sysUserService.authLogin(account, password);
        log.info("用户登录获取数据测试成功");
    }
}
