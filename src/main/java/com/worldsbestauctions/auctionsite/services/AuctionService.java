package com.worldsbestauctions.auctionsite.services;

import com.worldsbestauctions.auctionsite.entities.Auctions;
import com.worldsbestauctions.auctionsite.repos.AuctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionService {

    @Autowired
    AuctionRepo auctionPostRepo;

    public Iterable search (String value){
        return auctionPostRepo.findDistinctByTitleContainingOrDescriptionContaining(value, value);
    }

    public Iterable getAll(){
        return auctionPostRepo.findAllByOrderByStarttime();
    }


    public Auctions save(Auctions s) {
        return auctionPostRepo.save(s);
    }
}
