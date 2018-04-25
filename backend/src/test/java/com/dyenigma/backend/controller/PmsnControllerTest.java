package com.dyenigma.backend.controller;

import com.dyenigma.backend.BackendApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.Filter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * backend/com.dyenigma.backend.controller
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/25 18:09
 */
@Slf4j
public class PmsnControllerTest extends BackendApplicationTests {
    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilter((Filter) wac.getBean("shiroFilter")).build();
    }

    @Test
    public void getAllPmsnTest() throws Exception {

        String responseString = mockMvc.perform(get("/pmsn/getAllPmsn"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        log.info("--------返回的json = " + responseString);
    }
}
