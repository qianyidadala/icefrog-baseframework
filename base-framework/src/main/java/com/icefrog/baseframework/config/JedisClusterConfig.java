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

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis Cluster 配置类
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "spring.redis.jedis.cluster", name = "enabled")
@Import(RedisProperties.class)
@Getter
@Setter
public class JedisClusterConfig {

    /**
     * Configuration properties for Redis
     */
    @Autowired
    private RedisProperties redisProperties;

    /**
     * 构建redis集群信息
     */
    @Bean(name = "jedisCluster")
    public JedisCluster getJedisCluster() {
        Set<HostAndPort> nodes = new HashSet<>();
        // 分割出集群节点
        redisProperties.getCluster().getNodes().forEach(node -> {
            String[] hp = StringUtils.split(node, ":");
            nodes.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
        });
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive());
        jedisPoolConfig.setMaxIdle(redisProperties.getJedis().getPool().getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getJedis().getPool().getMaxWait().toMillis());
        jedisPoolConfig.setMinIdle(redisProperties.getJedis().getPool().getMinIdle());
        // 创建集群对象
        JedisCluster jedisCluster = new JedisCluster(nodes,
            new Long(redisProperties.getTimeout().getSeconds()).intValue(), jedisPoolConfig);
        // 注册jvm关闭钩子 关闭redis cluster
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                jedisCluster.close();
                log.info("jedisCluster close");
            } catch (IOException e) {
                log.error("close redis cluster error !!!", e);
            }
        }));
        return jedisCluster;
    }
}
