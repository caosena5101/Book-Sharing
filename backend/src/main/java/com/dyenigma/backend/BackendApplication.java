package com.dyenigma.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * backend/com.dyenigma
 *
 * @Description : 启动类
 * @Author : dingdongliang
 * @Date : 2018/4/3 8:30
 */
@MapperScan("com.dyenigma.backend.dao")
@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
