package com.bigfans.reviewservice.model.entity;

import com.bigfans.framework.model.AbstractModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author lichong
 * @create 2018-04-14 上午7:49
 **/
@Data
@Table(name = "OrderItem_Survey")
public class OrderItemSurveyEntity extends AbstractModel{

    @Column(name = "pg_id")
    protected String pgId;
    @Column(name = "prod_id")
    protected String prodId;
    @Column(name = "prod_name")
    protected String prodName;
    @Column(name = "prod_img")
    protected String prodImg;
    @Column(name = "price")
    protected BigDecimal price;
    @Column(name = "user_id")
    protected String userId;
    @Column(name = "orderitem_id")
    protected String orderItemId;
    @Column(name = "order_id")
    protected String orderId;


    @Override
    public String getModule() {
        return "OrderItemSurvey";
    }
}
