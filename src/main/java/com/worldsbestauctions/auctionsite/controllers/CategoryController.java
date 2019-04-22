package com.worldsbestauctions.auctionsite.controllers;

import com.worldsbestauctions.auctionsite.entities.Category;
import com.worldsbestauctions.auctionsite.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    Iterable<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
}
