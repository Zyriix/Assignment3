package com.bigfans.reviewservice.clients;

import com.bigfans.framework.exception.ServiceRuntimeException;
import com.bigfans.framework.utils.BeanUtils;
import com.bigfans.framework.web.RestResponse;
import com.bigfans.reviewservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class UserServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    public CompletableFuture<User> getUser(String userId) throws ServiceRuntimeException {
        return CompletableFuture.supplyAsync(() -> {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity requestEntity = new HttpEntity(null, headers);
            UriComponents builder = UriComponentsBuilder.fromUriString("http://user-service/users/{userId}").build().expand(userId).encode();
            ResponseEntity<RestResponse> responseEntity = restTemplate.exchange(builder.toUri(), HttpMethod.GET, requestEntity, RestResponse.class);
            RestResponse restResponse = responseEntity.getBody();
            Map data = (Map) restResponse.getData();
            User user = BeanUtils.mapToModel(data, User.class);
            return user;
        });
    }

}
