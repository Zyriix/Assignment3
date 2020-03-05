package com.bigfans.reviewservice.listener;

import com.bigfans.framework.kafka.KafkaConsumerBean;
import com.bigfans.framework.kafka.KafkaListener;
import com.bigfans.model.event.user.UserRegisteredEvent;
import com.bigfans.reviewservice.clients.UserServiceClient;
import com.bigfans.reviewservice.model.User;
import com.bigfans.reviewservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author lichong
 * @create 2018-03-24 上午11:17
 **/
@Component
@KafkaConsumerBean
public class UserListener {

    @Autowired
    private UserServiceClient userServiceClient;
    @Autowired
    private UserService userService;

    @KafkaListener
    public void on(UserRegisteredEvent event){
        String userId = event.getUserId();
        try {
            CompletableFuture<User> future = userServiceClient.getUser(userId);
            User user = future.get();
            userService.create(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
