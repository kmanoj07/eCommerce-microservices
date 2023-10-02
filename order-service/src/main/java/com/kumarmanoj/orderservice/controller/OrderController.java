package com.kumarmanoj.orderservice.controller;

import com.kumarmanoj.orderservice.dto.OrderRequest;
import com.kumarmanoj.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

//    public OrderController(OrderService orderService){
//        this.orderService = orderService;
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name="inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }
    public String fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {
        return "Oops something went wrong, please order after some time!";
    }
}
