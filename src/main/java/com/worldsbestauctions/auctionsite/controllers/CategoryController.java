package com.worldsbestauctions.auctionsite.controllers;

import com.worldsbestauctions.auctionsite.entities.Category;
import com.worldsbestauctions.auctionsite.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    List<Category> getAllCategories(){
        return ((List<Category>) categoryService.getAllCategories())
                .stream()
                .sorted(Comparator.comparing(Category::getCategoryname))
                .collect(Collectors.toList());
    }
}
