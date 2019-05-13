package com.zx.seckill.redis;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 17:24
 * @description：
 * @modified By：
 * @version: $
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisConfig {
    private String host;
    private int port;
    private int timeout;
    private String password;
    private int poolMaxTotal;
    private int poolMaxIdle;
    private int poolMaxWait;
}
