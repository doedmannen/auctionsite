package com.worldsbestauctions.auctionsite.controllers;


import com.worldsbestauctions.auctionsite.entities.Auctions;
import com.worldsbestauctions.auctionsite.entities.Images;
import com.worldsbestauctions.auctionsite.services.AuctionService;
import com.worldsbestauctions.auctionsite.services.ImageService;
import com.worldsbestauctions.auctionsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
@RequestMapping("/api/auction")
public class AuctionController {

    @Autowired
    AuctionService auctionPostsService;

    @Autowired
    ImageService imageService;

    @Autowired
    UserService userService;

    /*@GetMapping
    Iterable<Auctions> getTopTenAuctionPosts(){
        return auctionPostsService.getTopTen();
    }*/

    @GetMapping
    Iterable<Auctions> getAll(){
        return auctionPostsService.getAll();
    }

   /* @GetMapping
    Iterable<Auctions>getCategorys(){return auctionPostsService.getCategorys();}*/

    @PostMapping //todo set route to only allow authorized users
    long createNewAuction(@RequestBody Auctions body){
        // todo this is only used for testing, needs to get the user from session after login is made available
        body.setAuctionowner(userService.getMockUser().getUserid());
        body.setStarttime(LocalDateTime.now());
        body.setEndtime(LocalDateTime.now().plusDays(2));
        long id = auctionPostsService.save(body).getAuctionid();
        for(String path : body.getImages()){
            imageService.save(new Images(id, path));
        }
        return id;
    }

}
