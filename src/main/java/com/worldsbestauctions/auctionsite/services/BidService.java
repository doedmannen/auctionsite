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
    @Autowired
    UserRepo userRepo;
    @Autowired
    SocketService socketService;

    public void incommingBid(Bids bid, HttpServletRequest request){
        long bidId, userId;
        userId = userRepo.findDistinctFirstByEmailIgnoreCase(request.getUserPrincipal().getName()).getUserid();
        bid.setBidtime(LocalDateTime.now());
        bid.setUserid(userId);
        bidId = bidRepo.save(bid).getId();
        Bids b = bidRepo.findDistinctById(bidId);
        b.setUser(userRepo.findById(userId).get());
        socketService.sendToAll(new SocketWrapper(b), SocketWrapper.class);
    }

    public Iterable<Bids> getHightestBidById(int id){
        return bidRepo.findBidamountByAuctionid(id);
    }

}
