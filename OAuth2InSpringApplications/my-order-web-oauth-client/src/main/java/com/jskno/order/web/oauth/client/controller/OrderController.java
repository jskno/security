package com.jskno.order.web.oauth.client.controller;

import com.jskno.order.web.oauth.client.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @GetMapping("/orders")
    public String getOrders(Model model) {
         List<Order> orders = new ArrayList<>();
         model.addAttribute("orders", orders);

         return "orders-page";

    }
}
