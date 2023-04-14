package com.example.shopify_clothes.repository;

import com.example.shopify_clothes.model.Catlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatlogRepository extends JpaRepository<Catlog,Long> {

    List<Catlog> findByCategory(String s);



}
