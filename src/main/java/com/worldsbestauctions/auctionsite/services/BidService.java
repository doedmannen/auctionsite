package com.worldsbestauctions.auctionsite.services;

import com.worldsbestauctions.auctionsite.repos.BidRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidService {

    @Autowired
    BidRepo bidRepo;
}
