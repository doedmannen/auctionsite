package com.worldsbestauctions.auctionsite.controllers;


import com.worldsbestauctions.auctionsite.entities.AuctionPost;
import com.worldsbestauctions.auctionsite.services.AuctionPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auction")
public class AuctionPostController {

    @Autowired
    AuctionPostsService auctionPostsService;

    @GetMapping
    Iterable<AuctionPost> getAllWorlds(){
        return auctionPostsService.getAll();
    }
}
