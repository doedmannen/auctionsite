package com.worldsbestauctions.auctionsite.services;

import com.worldsbestauctions.auctionsite.entities.Bids;
import com.worldsbestauctions.auctionsite.repos.BidRepo;
import com.worldsbestauctions.auctionsite.repos.UserRepo;
import com.worldsbestauctions.auctionsite.socketwrapper.SocketWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

import java.util.List;

@Service
public class BidService {

    @Autowired
    BidRepo bidRepo;

    public Bids findById(long id){
        return bidRepo.findDistinctById(id);
    }

    public Iterable<Bids> getHightestBidById(int id){
        return bidRepo.findBidamountByAuctionid(id);
    }

    public Bids save(Bids b){
        return bidRepo.save(b);
    }
}
