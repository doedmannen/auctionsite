package com.worldsbestauctions.auctionsite.services;

import com.worldsbestauctions.auctionsite.entities.Bids;
import com.worldsbestauctions.auctionsite.repos.BidRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class BidService {

    @Autowired
    BidRepo bidRepo;


    public Bids save(Bids bid){
        return bidRepo.save(bid);
    }

    public Iterable<Bids> getHightestBidById(int id){
        return bidRepo.findBidamountByAuctionid(id);
    }

}
