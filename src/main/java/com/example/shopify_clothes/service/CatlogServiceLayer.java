package com.example.shopify_clothes.service;


import com.example.shopify_clothes.model.Catlog;
import com.example.shopify_clothes.repository.CatlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatlogServiceLayer {

    @Autowired
    public CatlogRepository catlogRepo;

    public List<Catlog> allCatlog(){
        return catlogRepo.findAll();
    }
    public void delete(Long id){catlogRepo.deleteById(id);}

    public List<Catlog> categoryLog(String category){
        return catlogRepo.findByCategory(category);
    }

    public Catlog findById(Long id){
        return catlogRepo.findById(id).orElse(null);
    }

    public void save(Catlog c){
        catlogRepo.save(c);
    }


}
