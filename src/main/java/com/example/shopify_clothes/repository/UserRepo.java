package com.example.shopify_clothes.repository;

import com.example.shopify_clothes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {


    User findByEmail(String i);

}
