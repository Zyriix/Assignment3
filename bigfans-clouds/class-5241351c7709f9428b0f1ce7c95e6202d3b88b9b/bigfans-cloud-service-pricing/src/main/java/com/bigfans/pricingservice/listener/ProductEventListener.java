package com.bigfans.pricingservice.listener;

import com.bigfans.framework.kafka.KafkaConsumerBean;
import com.bigfans.framework.kafka.KafkaListener;
import com.bigfans.model.event.ProductCreatedEvent;
import com.bigfans.pricingservice.api.clients.CatalogServiceClient;
import com.bigfans.pricingservice.model.Product;
import com.bigfans.pricingservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;

/**
 * @author lichong
 * @create 2018-02-17 下午6:29
 **/
@KafkaConsumerBean
public class ProductEventListener {

    @Autowired
    private ProductService productService;
    @Autowired
    private CatalogServiceClient catalogServiceClient;

    @KafkaListener
    public void on(ProductCreatedEvent event){
        try {
            String prodId = event.getId();
            CompletableFuture<Product> prodFuture = catalogServiceClient.getProductById(prodId);

            Product product = prodFuture.get();
            productService.create(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
