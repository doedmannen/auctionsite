package com.worldsbestauctions.auctionsite.services;

import com.worldsbestauctions.auctionsite.repos.AuctionPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionPostsService {

    @Autowired
    AuctionPostRepo auctionPostRepo;



}
