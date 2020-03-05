package com.bigfans.reviewservice.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderItem {

    protected String id;
    protected Date createDate;
    protected Date updateDate;

    protected String orderId;
    protected String userId;
    /* 产品id */
    protected String pgId;
    protected String prodId;
    protected String prodName;
    protected String prodImg;
    /* 产品成交总价 */
    protected BigDecimal dealSubTotal;
    /* 购买数量 */
    protected Integer quantity;
    // 评价状态
    protected Integer commentStatus;
}
