package com.worldsbestauctions.auctionsite.controllers;


import com.worldsbestauctions.auctionsite.entities.Auctions;
import com.worldsbestauctions.auctionsite.entities.Images;
import com.worldsbestauctions.auctionsite.services.AuctionService;
import com.worldsbestauctions.auctionsite.services.ImageService;
import com.worldsbestauctions.auctionsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auction")
public class AuctionController {

    @Autowired
    AuctionService auctionPostsService;

    @Autowired
    ImageService imageService;

    @Autowired
    UserService userService;

    @GetMapping
    Iterable<Auctions> getAll(){
        return auctionPostsService.getTopTen();
    }
    
    /*@GetMapping
    Iterable<Auctions> getAll(){
        return auctionPostsService.getAll();
    }*/

    @PostMapping
    long createNewAuction(@RequestBody Auctions body, HttpServletRequest request){
        body.setAuctionowner(userService.getUserByEmail(request.getUserPrincipal().getName()).getUserid());
        body.setStarttime(LocalDateTime.now());
        body.setEndtime(LocalDateTime.now().plusDays(2));
        long id = auctionPostsService.save(body).getAuctionid();
        for(String path : body.getImages()){
            imageService.save(new Images(id, path));
        }
        return id;
    }

}
