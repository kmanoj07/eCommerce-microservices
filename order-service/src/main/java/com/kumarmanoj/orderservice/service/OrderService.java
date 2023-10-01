package com.kumarmanoj.orderservice.service;

import com.kumarmanoj.orderservice.dto.OrderLineItemsDto;
import com.kumarmanoj.orderservice.dto.OrderRequest;
import com.kumarmanoj.orderservice.model.Order;
import com.kumarmanoj.orderservice.model.OrderLineItems;
import com.kumarmanoj.orderservice.repository.OrderServiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderServiceRepository orderServiceRepository;
    private final WebClient webClient;

    public OrderService(OrderServiceRepository orderServiceRepository, WebClient webClient){
            this.orderServiceRepository = orderServiceRepository;
            this.webClient = webClient;
    }
    public void placeOrder(OrderRequest orderRequest) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());

            List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList()
                    .stream()
                    .map(orderLineItemsDto -> mapToOrderLineItems(orderLineItemsDto)).toList();

            order.setOrderLineItemsList(orderLineItemsList);

        // Communicate with the inventory-service using WebClient (WebFlux project) to get the stock status - true/false
        // Inventory service running on 8083
        // Synchronous request
        Boolean result = webClient.get()
                .uri("http://localhost:8083/api/inventory")
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if(result){
            //Save to DB
            orderServiceRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in Stock, Please try again later");
        }
    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .skuCode(orderLineItemsDto.getSkuCode())
                .quantity(orderLineItemsDto.getQuantity())
                .price(orderLineItemsDto.getPrice())
                .build();
    }
}
