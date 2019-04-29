package com.worldsbestauctions.auctionsite.controllers;

import com.worldsbestauctions.auctionsite.entities.Bids;
import com.worldsbestauctions.auctionsite.services.BidService;
import com.worldsbestauctions.auctionsite.services.SocketService;
import com.worldsbestauctions.auctionsite.services.UserService;
import com.worldsbestauctions.auctionsite.socketwrapper.SocketWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/bid")
public class BidController {

    @Autowired
    BidService bidService;

    @PostMapping
    void createNewBid(@RequestBody Bids body, HttpServletRequest request) {
        bidService.incommingBid(body, request);
        double oldBid=oldBid(body);
        if (oldBid>=body.getBidamount())
        {
            System.out.println("error, bid was too low");
        }
        else{
            body.setBidamount(body.getBidamount());
            body.setBidtime(LocalDateTime.now());
            body.setUserid(userService.getUserByEmail(request.getUserPrincipal().getName()).getUserid());
            bidService.save(body);
        }
    }

    public double oldBid(Bids body){
        double previousHighestBid=0;
        int auctionId = body.getAuctionid();
        Iterable<Bids>bidAmount=bidService.getHightestBidById(auctionId);
        for(Bids bid:bidAmount){
            if(bid.getBidamount()>previousHighestBid)
                previousHighestBid=bid.getBidamount();
        }
        System.out.println(previousHighestBid+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return previousHighestBid;
    }
}
