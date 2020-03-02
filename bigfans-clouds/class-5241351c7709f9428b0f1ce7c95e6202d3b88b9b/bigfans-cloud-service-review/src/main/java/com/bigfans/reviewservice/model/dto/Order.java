package com.bigfans.reviewservice.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Order {

    protected String id;
    protected Date createDate;
    protected Date updateDate;
    protected Integer deleted = 0;

    // 订单状态
    protected String status;
    // 取消原因
    protected String cancelReason;
    // 用户信息
    protected String userId;
}
