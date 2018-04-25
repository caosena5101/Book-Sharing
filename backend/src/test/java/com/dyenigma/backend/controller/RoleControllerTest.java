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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * backend/com.dyenigma.backend.controller
 *
 * @Description : 角色操作接口测试类
 * @Author : dingdongliang
 * @Date : 2018/4/25 11:53
 */
@Slf4j
public class RoleControllerTest extends BackendApplicationTests {
    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilter((Filter) wac.getBean("shiroFilter")).build();
    }

    @Test
    public void roleListTest() throws Exception {
        String pageNo = "{\"pageNo\":\"1\"}";
        String responseString = mockMvc.perform(get("/role/roleList")
                .contentType(MediaType.APPLICATION_JSON).content(pageNo))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        log.info("--------返回的json = " + responseString);
    }

    @Test
    public void addRoleTest() throws Exception {
        String roleInfo = "{\"roleName\":\"破坏者\",\"permissions\":" +
                "\"2e0b4be914de494d99236f7d5141804a|6b12817ab5b943e1b4d4218617dd3ca3\"}";

        String responseString = mockMvc.perform(post("/role/addRole")
                .contentType(MediaType.APPLICATION_JSON).content(roleInfo))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        log.info("--------返回的json = " + responseString);
    }

    @Test
    public void updateRoleTest() throws Exception {
        String roleInfo = "{\"roleId\":\"1c8f0b55f40b43bbafbf8123b68b15e4\",\"roleName\":\"破坏王\",\"permissions\":" +
                "\"db8b14b18eff4b14ad0b986baba28cbc|6b12817ab5b943e1b4d4218617dd3ca3\",\"status\":\"E\"}";

        String responseString = mockMvc.perform(post("/role/updateRole")
                .contentType(MediaType.APPLICATION_JSON).content(roleInfo))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        log.info("--------返回的json = " + responseString);
    }

    @Test
    public void getAllPmsnTest() throws Exception {

        String responseString = mockMvc.perform(get("/role/getAllPmsn"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        log.info("--------返回的json = " + responseString);
    }

    @Test
    public void deleteRoleTest() throws Exception {
        String roleId = "{\"roleId\":\"21eec414f3454ec1abce6049863cafc9\"}";
        String responseString = mockMvc.perform(delete("/role/deleteRole")
                .contentType(MediaType.APPLICATION_JSON).content(roleId))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        log.info("--------返回的json = " + responseString);
    }
}
