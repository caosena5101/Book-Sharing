package com.dyenigma.sharing.config;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * sharing/com.dyenigma.sharing.config
 *
 * @Description : Druic监控Spring，目前没有起作用
 * @Author : dingdongliang
 * @Date : 2018/4/18 9:57
 */
@Configuration
public class DruidSpringConfigure {

    /**
     * 定义监听Spring拦截器
     *
     * @return com.alibaba.druid.support.spring.stat.DruidStatInterceptor
     * @author dingdongliang
     * @date 2018/4/18 10:31
     */
    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }

    /**
     * 定义监听Spring切入点
     *
     * @return org.springframework.aop.support.JdkRegexpMethodPointcut
     * @author dingdongliang
     * @date 2018/4/18 10:31
     */
    @Bean
    public JdkRegexpMethodPointcut druidStatPointcut() {
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        String patterns = "com.dyenigma.*.*.service.*";
        String patterns2 = "com.dyenigma.*.*.dao.*";
        pointcut.setPatterns(patterns, patterns2);
        return pointcut;
    }

    /**
     * 定义监听Spring通知类
     *
     * @return org.springframework.aop.Advisor
     * @author dingdongliang
     * @date 2018/4/18 10:31
     */
    @Bean
    public Advisor druidStatAdvisor() {
        return new DefaultPointcutAdvisor(druidStatPointcut(), druidStatInterceptor());
    }
}
