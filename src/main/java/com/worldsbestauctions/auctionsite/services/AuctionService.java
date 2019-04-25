package com.worldsbestauctions.auctionsite.services;

import com.worldsbestauctions.auctionsite.entities.Auctions;
import com.worldsbestauctions.auctionsite.repos.AuctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionService {

    @Autowired
    AuctionRepo auctionPostRepo;

    public Iterable getTopTen() {
        return auctionPostRepo.findTop10ByOrderByAuctionidDesc();
    }

    /*public Iterable getAll(){
        return auctionPostRepo.findAll();
    }*/


    public Auctions save(Auctions s) {
        return auctionPostRepo.save(s);
    }
}
