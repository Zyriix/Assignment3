package com.bigfans.pricingservice.api.clients;

import com.bigfans.framework.CurrentUser;
import com.bigfans.framework.utils.BeanUtils;
import com.bigfans.framework.web.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * @author lichong
 * @create 2018-02-16 下午5:27
 **/
@Component
public class UserServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    public CurrentUser getCurrentUser(String token){
        UriComponents builder = UriComponentsBuilder.fromUriString("http://user-service/currentUser?token={token}").build().expand(token).encode();
        ResponseEntity<RestResponse> responseEntity = restTemplate.getForEntity(builder.toUri(), RestResponse.class);
        RestResponse response = responseEntity.getBody();
        CurrentUser cu = BeanUtils.mapToModel((Map) response.getData(), CurrentUser.class);
        return cu;
    }
}
