package com.dyenigma.sharing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * backend/com.dyenigma.sharing.config
 *
 * @Description : Redis 配置Session共享
 * @Author : dingdongliang
 * @Date : 2018/3/29 15:40
 */
@Configuration
@EnableRedisHttpSession
public class RedisClusterConfigure {
}
