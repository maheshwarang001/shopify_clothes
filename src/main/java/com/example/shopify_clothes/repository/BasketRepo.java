package com.example.shopify_clothes.repository;

import com.example.shopify_clothes.model.Basket;
import com.example.shopify_clothes.model.Catlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepo extends JpaRepository<Basket,Long> {

    Basket findByCatlogs(Catlog c);

    Basket findByCatlogs_CatlogID(Long catlogID);
}
