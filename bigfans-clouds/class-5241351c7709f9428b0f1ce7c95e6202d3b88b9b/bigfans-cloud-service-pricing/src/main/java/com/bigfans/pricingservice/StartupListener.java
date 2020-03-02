package com.bigfans.pricingservice;

import com.bigfans.framework.BeanProviderSpring;
import com.bigfans.framework.kafka.KafkaConsumerBean;
import com.bigfans.framework.kafka.KafkaConsumerTaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lichong
 * @create 2018-02-24 上午10:01
 **/
@Component
public class StartupListener implements CommandLineRunner {

    @Autowired
    private KafkaConsumerTaskManager consumerTaskManager;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run(String... arg0) throws Exception {
        Map<String, Object> beansWithAnnotation = this.applicationContext.getBeansWithAnnotation(KafkaConsumerBean.class);
        consumerTaskManager.registerListeners(beansWithAnnotation.values().iterator());
        consumerTaskManager.consume();

        BeanProviderSpring.initContext(applicationContext);
    }
}
