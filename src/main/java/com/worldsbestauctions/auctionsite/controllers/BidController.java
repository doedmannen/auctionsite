package com.worldsbestauctions.auctionsite.controllers;

import com.worldsbestauctions.auctionsite.services.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bid")
public class BidController {

    @Autowired
    BidService bidService;
}
