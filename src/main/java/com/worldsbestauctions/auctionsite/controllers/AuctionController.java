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


    @GetMapping("/search/{value}")
    Iterable<Auctions> search(@PathVariable String value){
        return auctionPostsService.search(value);
    }
    
    @GetMapping
    Iterable<Auctions> getAll(){
        return auctionPostsService.getAll();
    }

    @PostMapping
    long createNewAuction(@RequestBody Auctions body, HttpServletRequest request){
        return auctionPostsService.create(request.getUserPrincipal().getName(), body);

    }

}
