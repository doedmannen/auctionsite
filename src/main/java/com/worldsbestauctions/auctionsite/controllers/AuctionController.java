package com.worldsbestauctions.auctionsite.controllers;


import com.worldsbestauctions.auctionsite.entities.Auctions;
import com.worldsbestauctions.auctionsite.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auction")
public class AuctionController {

    @Autowired
    AuctionService auctionPostsService;

    /*@GetMapping
    Iterable<Auctions> getTopTenAuctionPosts(){
        return auctionPostsService.getTopTen();
    }*/

    @GetMapping
    Iterable<Auctions> getAll(){
        return auctionPostsService.getTopTen();
    }
}
