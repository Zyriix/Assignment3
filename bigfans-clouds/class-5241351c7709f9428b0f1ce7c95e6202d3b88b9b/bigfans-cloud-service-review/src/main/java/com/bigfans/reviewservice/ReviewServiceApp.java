package com.bigfans.reviewservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lichong
 * @create 2018-02-14 下午8:05
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ReviewServiceApp {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(ReviewServiceApp.class , args);
    }

}
