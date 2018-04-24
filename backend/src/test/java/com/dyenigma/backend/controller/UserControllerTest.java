package com.dyenigma.backend.controller;

import com.dyenigma.backend.BackendApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.Filter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * backend/com.dyenigma.backend.controller
 *
 * @Description : 用户操作接口测试类，测试的时候，需要去除权限控制
 * @Author : dingdongliang
 * @Date : 2018/4/24 10:51
 */
@Slf4j
public class UserControllerTest extends BackendApplicationTests {
    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilter((Filter) wac.getBean("shiroFilter")).build();
    }

    @Test
    public void userListTest() throws Exception {

        String responseString = mockMvc.perform(get("/user/userList"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        log.info("--------返回的json = " + responseString);
    }

    @Test
    public void addUserTest() throws Exception {
        String userInfo = "{\"userName\":\"admin\",\"password\":\"admin\",\"realName\":\"good man\"," +
                "\"roleId\":\"64205b16f5d04b47aea4b091d88c243e|8cefc3f9409348bb9677118aed62fdfb" +
                "|fb7f035401204cff8c58f240b866c925\"}";

        String responseString = mockMvc.perform(post("/user/addUser")
                .contentType(MediaType.APPLICATION_JSON).content(userInfo))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        log.info("--------返回的json = " + responseString);
    }

    @Test
    public void updateUserTest() throws Exception {
        String userInfo = "{\"userId\":\"9045b033e3ad42b0bf7819a228dd50ee\",\"realName\":\"good man!\"," +
                "\"roleId\":\"8cefc3f9409348bb9677118aed62fdfb|fb7f035401204cff8c58f240b866c925\",\"status\":\"E\"}";

        String responseString = mockMvc.perform(post("/user/updateUser")
                .contentType(MediaType.APPLICATION_JSON).content(userInfo))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        log.info("--------返回的json = " + responseString);
    }

    @Test
    public void getAllRolesTest() throws Exception {

        String responseString = mockMvc.perform(post("/user/getAllRoles"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        log.info("--------返回的json = " + responseString);
    }
}
