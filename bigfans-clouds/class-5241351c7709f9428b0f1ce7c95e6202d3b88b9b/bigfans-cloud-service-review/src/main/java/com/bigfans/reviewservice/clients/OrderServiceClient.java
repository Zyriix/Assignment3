package com.bigfans.reviewservice.clients;

import com.bigfans.framework.utils.BeanUtils;
import com.bigfans.framework.web.RestResponse;
import com.bigfans.reviewservice.model.dto.Order;
import com.bigfans.reviewservice.model.dto.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class OrderServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    public CompletableFuture<Order> getOrder(String orderId) {
        return CompletableFuture.supplyAsync(() -> {
            UriComponents builder = UriComponentsBuilder.fromUriString("http://order-service/orders/{orderId}").build().expand(orderId).encode();
            ResponseEntity<RestResponse> responseEntity = restTemplate.getForEntity(builder.toUri(), RestResponse.class);
            RestResponse resp = responseEntity.getBody();
            Map data = (Map) resp.getData();
            Order category = BeanUtils.mapToModel(data, Order.class);
            return category;
        });
    }

    public CompletableFuture<List<OrderItem>> getOrderItems(String orderId) {
        return CompletableFuture.supplyAsync(() -> {
            UriComponents builder = UriComponentsBuilder.fromUriString("http://order-service/orderItems?orderId={orderId}").build().expand(orderId).encode();
            ResponseEntity<RestResponse> responseEntity = restTemplate.getForEntity(builder.toUri(), RestResponse.class);
            RestResponse resp = responseEntity.getBody();
            List data = (List) resp.getData();
            List<OrderItem> items = new ArrayList<>();
            for(int i = 0;i<data.size() ; i++){
                OrderItem orderItem = BeanUtils.mapToModel((Map)data.get(i), OrderItem.class);
                items.add(orderItem);
            }
            return items;
        });
    }
}
