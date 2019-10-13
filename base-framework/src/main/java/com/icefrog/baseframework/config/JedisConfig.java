/***
 * Copyright 2019 Icefrog
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.icefrog.baseframework.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis Pool 配置类
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "spring.redis.jedis.single", name = "enabled")
@Import(RedisProperties.class)
public class JedisConfig {

    /**
     * Configuration properties for Redis
     */
    @Autowired
    private RedisProperties redisProperties;

    @Bean("jedisPool")
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive());
        jedisPoolConfig.setMaxIdle(redisProperties.getJedis().getPool().getMaxIdle());
        jedisPoolConfig
            .setMaxWaitMillis(redisProperties.getJedis().getPool().getMaxWait().toMillis());
        jedisPoolConfig.setMinIdle(redisProperties.getJedis().getPool().getMinIdle());
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisProperties.getHost(),
            redisProperties.getPort(), redisProperties.getTimeout().getNano(),
            redisProperties.getPassword(), redisProperties.getDatabase());
        // 注册jvm关闭钩子 关闭redissonClient
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (!jedisPool.isClosed()) {
                jedisPool.close();
                log.info("jedisPool close !!!");
            }
        }));
        return jedisPool;
    }
}
