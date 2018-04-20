package com.dyenigma.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * backend/com.dyenigma.backend.config
 *
 * @Description : Redis 配置Session共享
 * @Author : dingdongliang
 * @Date : 2018/3/29 15:40
 */
@Configuration
@EnableRedisHttpSession
public class RedisClusterConfigure {
}
