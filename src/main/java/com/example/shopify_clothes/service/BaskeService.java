package com.example.shopify_clothes.service;


import com.example.shopify_clothes.model.Basket;
import com.example.shopify_clothes.repository.BasketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BaskeService {

    @Autowired
    BasketRepo basketRepo;

    public void savebasket(Basket basket){

        basketRepo.save(basket);

    }


    public List<Basket> getallBasket(){
        return basketRepo.findAll();
    }

    public Basket findbypid(Long c){

       return basketRepo.findByCatlogs_CatlogID(c);
    }

    public void delete(Long id){
        basketRepo.deleteById(id);
    }
    public Optional<Basket> findbybasketID(Long id){
        return basketRepo.findById(id);
    }

    public  void deleteAll(){ basketRepo.deleteAll();}
}
