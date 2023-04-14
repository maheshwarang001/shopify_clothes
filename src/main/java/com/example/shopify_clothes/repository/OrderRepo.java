package com.example.shopify_clothes.repository;

import com.example.shopify_clothes.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Long> {
}
