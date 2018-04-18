package com.dyenigma.sharing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * backend/com.dyenigma.sharing.config
 *
 * @Description : 设置默认页面路径，主页设置为自动文档页面
 * @Author : dingdongliang
 * @Date : 2018/4/3 8:31
 */
@Configuration
public class HomePageConfigurer implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/swagger-ui.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
