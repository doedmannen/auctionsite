package com.worldsbestauctions.auctionsite.controllers;

import com.worldsbestauctions.auctionsite.entities.Auctions;
import com.worldsbestauctions.auctionsite.entities.Bids;
import com.worldsbestauctions.auctionsite.services.AuctionService;
import com.worldsbestauctions.auctionsite.services.BidService;
import com.worldsbestauctions.auctionsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/bid")
public class BidController {

    @Autowired
    BidService bidService;
    @Autowired
    UserService userService;
    @Autowired
    AuctionService auctionService;

    @PostMapping
    void createNewBid(@RequestBody Bids body, HttpServletRequest request) {
        auctionOwner(body, request);
        double oldBid = previousHighestBid(body);
        if (oldBid >= body.getBidamount() || auctionOwner(body, request)) {
            System.out.println("error, faulty input");
        } else {
            body.setBidamount(body.getBidamount());
            body.setBidtime(LocalDateTime.now());
            body.setUserid(userService.getUserByEmail(request.getUserPrincipal().getName()).getUserid());
            bidService.save(body);
        }
    }

    public double previousHighestBid(Bids body) {
        double previousHighestBid = 0;
        int auctionId = body.getAuctionid();
        Iterable<Bids> bidAmount = bidService.getHightestBidById(auctionId);
        for (Bids bid : bidAmount) {
            if (bid.getBidamount() > previousHighestBid)
                previousHighestBid = bid.getBidamount();
        }
        return previousHighestBid;
    }

    public boolean auctionOwner(Bids body, HttpServletRequest request) {
        boolean isOwner = false;
        Optional<Auctions> allAuctions = auctionService.findById(body.getAuctionid());
        try{
            if(allAuctions.get().getAuctionowner() == userService.getUserByEmail(request.getUserPrincipal().getName()).getUserid()){
                isOwner = true;
            }
        }catch (Exception e){}
        return isOwner;
    }
}
