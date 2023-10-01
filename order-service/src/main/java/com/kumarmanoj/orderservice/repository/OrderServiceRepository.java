package com.kumarmanoj.orderservice.repository;

import com.kumarmanoj.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderServiceRepository extends JpaRepository<Order, Long> {
}
