package com.jskno.order.resource.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private OrderStatus status;


}
