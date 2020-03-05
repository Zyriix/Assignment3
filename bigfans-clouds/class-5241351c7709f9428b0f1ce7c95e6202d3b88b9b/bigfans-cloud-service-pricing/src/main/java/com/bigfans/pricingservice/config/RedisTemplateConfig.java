package com.bigfans.pricingservice.config;

import com.bigfans.framework.redis.JedisConnectionFactory;
import com.bigfans.framework.redis.JedisTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisTemplateConfig {

    @Value("${jedis.host}")
    private String host;
    @Value("${jedis.port}")
    private Integer port;
    @Value("${jedis.auth}")
    private String auth;
    @Value("${jedis.max_idle}")
    private Integer max_idle;
    @Value("${jedis.timeout}")
    private Integer timeout;
    @Value("${jedis.test_on_borrow}")
    private Boolean test_on_borrow;
    @Value("${jedis.default_db_index}")
    private Integer default_db_index;

    @Bean(name = "jedisTemplate")
    public JedisTemplate createTemplate() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setAuth(auth);
        factory.setMax_idle(max_idle);
        factory.setTimeout(timeout);
        factory.setTest_on_borrow(test_on_borrow);
        factory.setDefaultDbIndex(default_db_index);
        JedisTemplate jedisTemplate = new JedisTemplate(factory);
        return jedisTemplate;
    }

}
