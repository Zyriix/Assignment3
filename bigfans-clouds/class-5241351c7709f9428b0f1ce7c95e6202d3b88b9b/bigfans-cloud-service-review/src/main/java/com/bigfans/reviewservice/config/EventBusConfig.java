package com.bigfans.reviewservice.config;

import com.bigfans.framework.event.ApplicationEventBus;
import com.bigfans.framework.event.EventRepository;
import com.bigfans.framework.event.JdbcEventRepository;
import com.bigfans.framework.event.SpringEventBus;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author lichong
 * @create 2018-02-05 下午8:33
 **/
@Configuration
public class EventBusConfig implements ApplicationContextAware{

    private ApplicationContext applicationContext;

    @Bean
    public EventRepository eventRepository(JdbcTemplate jdbcTemplate){
        return new JdbcEventRepository(jdbcTemplate);
    }

    @Bean
    public ApplicationEventBus eventBus(EventRepository eventRepository){
        return new SpringEventBus(applicationContext);
    };

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
