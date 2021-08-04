package com.userservice.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseOrders {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDateTime createdAt;

    private String orderId;
}
