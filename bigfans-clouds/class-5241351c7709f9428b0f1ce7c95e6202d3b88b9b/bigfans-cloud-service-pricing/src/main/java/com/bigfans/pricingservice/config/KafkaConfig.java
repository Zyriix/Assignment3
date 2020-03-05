package com.bigfans.pricingservice.config;

import com.bigfans.framework.kafka.KafkaConsumerTaskManager;
import com.bigfans.framework.kafka.KafkaFactory;
import com.bigfans.framework.kafka.KafkaTemplate;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author lichong
 * @create 2018-02-04 上午10:46
 **/
@Configuration
public class KafkaConfig {

    @Value("${kafka.servers}")
    private String servers;
    @Value("${kafka.groupId}")
    private String groupId;
    @Value("${kafka.clientId}")
    private String clientId;

    @Bean
    public KafkaFactory kafkaFactory(){

        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        producerProperties.put(ProducerConfig.ACKS_CONFIG, "all");
        producerProperties.put(ProducerConfig.RETRIES_CONFIG, 0);
        producerProperties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        producerProperties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        producerProperties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        Properties consumerProperties = new Properties();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        consumerProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "simple");

        KafkaFactory factory = new KafkaFactory();
        factory.setConsumerProperties(consumerProperties);
        factory.setProducerProperties(producerProperties);
        return factory;
    }

    @Bean
    public KafkaConsumerTaskManager kafkaConsumerManager(KafkaFactory kafkaFactory){
        return new KafkaConsumerTaskManager(kafkaFactory);
    }

    @Bean
    public KafkaTemplate kafkaTemplate(KafkaFactory kafkaFactory){
        return new KafkaTemplate(kafkaFactory);
    }
}
