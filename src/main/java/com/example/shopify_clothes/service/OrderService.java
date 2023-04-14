package com.example.shopify_clothes.service;


import com.example.shopify_clothes.model.Order;
import com.example.shopify_clothes.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    public boolean checkoutservice(Order order){
        Order savedOrder = orderRepo.save(order);
        return savedOrder != null;
    }

    public List<Order> findAll(){
        return orderRepo.findAll();
    }
}
