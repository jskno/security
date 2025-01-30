package com.jskno.order.resource.server.controller;

import com.jskno.order.resource.server.dto.OrderDto;
import com.jskno.order.resource.server.dto.OrderStatus;
import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @GetMapping
    public List<OrderDto> getOrders() {
        log.info("getOrders reached");
        return List.of(
            OrderDto.builder().orderId(UUID.randomUUID().toString()).productId("product1").userId("user1").quantity(10).status(
                OrderStatus.APPROVED).build(),
            OrderDto.builder().orderId(UUID.randomUUID().toString()).productId("product2").userId("user2").quantity(20).status(
                OrderStatus.NEW).build()
            );
    }

}
