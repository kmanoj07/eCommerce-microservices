package com.kumarmanoj.orderservice.service;

import com.kumarmanoj.orderservice.dto.OrderLineItemsDto;
import com.kumarmanoj.orderservice.dto.OrderRequest;
import com.kumarmanoj.orderservice.model.Order;
import com.kumarmanoj.orderservice.model.OrderLineItems;
import com.kumarmanoj.orderservice.repository.OrderServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderServiceRepository orderServiceRepository;

    public OrderService(OrderServiceRepository orderServiceRepository){
            this.orderServiceRepository = orderServiceRepository;
        }
        public void placeOrder(OrderRequest orderRequest) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());

            List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList()
                    .stream()
                    .map(orderLineItemsDto -> mapToOrderLineItems(orderLineItemsDto)).toList();

            order.setOrderLineItemsList(orderLineItemsList);
            //Save to DB
            orderServiceRepository.save(order);
    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .skuCode(orderLineItemsDto.getSkuCode())
                .quantity(orderLineItemsDto.getQuantity())
                .price(orderLineItemsDto.getPrice())
                .build();
    }
}
