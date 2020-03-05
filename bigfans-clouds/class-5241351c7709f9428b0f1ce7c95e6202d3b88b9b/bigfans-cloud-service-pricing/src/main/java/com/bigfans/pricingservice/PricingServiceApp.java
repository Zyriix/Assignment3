package com.bigfans.pricingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lichong
 * @create 2018-02-15 下午2:52
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class PricingServiceApp {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(PricingServiceApp.class , args);
    }

}
