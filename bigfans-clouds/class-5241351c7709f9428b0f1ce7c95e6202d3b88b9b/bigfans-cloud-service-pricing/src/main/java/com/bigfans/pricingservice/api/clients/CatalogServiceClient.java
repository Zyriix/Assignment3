package com.bigfans.pricingservice.api.clients;

import com.bigfans.framework.utils.BeanUtils;
import com.bigfans.framework.web.RestResponse;
import com.bigfans.pricingservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author lichong
 * @create 2018-02-13 下午7:42
 **/
@Component
public class CatalogServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    public CompletableFuture<Product> getProductById(String prodId) {
        return CompletableFuture.supplyAsync(() -> {
            UriComponents builder = UriComponentsBuilder.fromUriString("http://catalog-service/attributes?prodId={prodId}").build().expand(prodId).encode();
            ResponseEntity<RestResponse> responseEntity = restTemplate.getForEntity(builder.toUri(), RestResponse.class);
            RestResponse response = responseEntity.getBody();
            Product product = BeanUtils.mapToModel((Map) response.getData() , Product.class);
            return product;
        });
    }
}
