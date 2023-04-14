package com.example.shopify_clothes.service;


import com.example.shopify_clothes.model.User;
import com.example.shopify_clothes.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Credential {

    @Autowired
    UserRepo userRepo;

    public User login(String username){
       return userRepo.findByEmail(username);
    }
}
