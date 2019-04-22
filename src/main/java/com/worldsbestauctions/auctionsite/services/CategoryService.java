package com.worldsbestauctions.auctionsite.services;

import com.worldsbestauctions.auctionsite.entities.Category;
import com.worldsbestauctions.auctionsite.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    public Iterable<Category> getAllCategories(){
        return categoryRepo.findAll();
    }
}
