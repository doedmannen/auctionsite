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
    @Autowired
    UserService userService;
    @Autowired
    SocketService socketService;

    @PostMapping
    void createNewBid(@RequestBody Bids body, HttpServletRequest request) {
        body.setBidtime(LocalDateTime.now());
        body.setUserid(userService.getUserByEmail(request.getUserPrincipal().getName()).getUserid());
        Bids bid = bidService.save(body);
        socketService.sendToAll(new SocketWrapper(bid), SocketWrapper.class);
    }
}
