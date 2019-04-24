package com.worldsbestauctions.auctionsite.services;

import com.worldsbestauctions.auctionsite.entities.Bids;
import com.worldsbestauctions.auctionsite.repos.BidRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class BidService {

    @Autowired
    BidRepo bidRepo;


    public Bids save(Bids bid){
        return bidRepo.save(bid);
    }

}
