package com.jskno.order.web.oauth.client.controller;

import com.jskno.order.web.oauth.client.model.Order;
import com.jskno.order.web.oauth.client.service.OrderResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderResourceService orderResourceService;

    @GetMapping("/orders")
    public String getOrders(Model model,
                    @RegisteredOAuth2AuthorizedClient("users-client-oidc") OAuth2AuthorizedClient authorizedClient) {

        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        System.out.println(accessToken);


         List<Order> orders = orderResourceService.getRestTemplateOrders(accessToken);
         model.addAttribute("orders", orders);

         return "orders-page";

    }
}
