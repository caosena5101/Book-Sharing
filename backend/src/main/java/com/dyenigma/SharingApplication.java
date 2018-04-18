package com.dyenigma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;


/**
 * backend/com.dyenigma
 *
 * @Description : 启动类
 * @Author : dingdongliang
 * @Date : 2018/4/3 8:30
 */
@MapperScan("com.dyenigma.sharing.dao")
@SpringBootApplication
public class SharingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharingApplication.class, args);
    }
}
