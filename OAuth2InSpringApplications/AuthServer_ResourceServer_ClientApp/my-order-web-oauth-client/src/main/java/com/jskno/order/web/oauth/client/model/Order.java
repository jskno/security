package com.jskno.order.web.oauth.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    private String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private OrderStatus status;
}
