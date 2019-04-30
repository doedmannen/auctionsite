package com.worldsbestauctions.auctionsite.services;

import com.worldsbestauctions.auctionsite.entities.Auctions;
import com.worldsbestauctions.auctionsite.entities.Bids;
import com.worldsbestauctions.auctionsite.entities.Notification;
import com.worldsbestauctions.auctionsite.entities.Users;
import com.worldsbestauctions.auctionsite.repos.AuctionRepo;
import com.worldsbestauctions.auctionsite.repos.BidRepo;
import com.worldsbestauctions.auctionsite.repos.NotificationRepo;
import com.worldsbestauctions.auctionsite.repos.UserRepo;
import com.worldsbestauctions.auctionsite.socketwrapper.SocketWrapper;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedInheritableThreadLocal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    AuctionRepo auctionRepo;
    @Autowired
    BidRepo bidRepo;
    @Autowired
    NotificationRepo notificationRepo;
    @Autowired
    SocketService socketService;

    public void createNotifications(Bids b){
        Auctions auction = auctionRepo.findById(b.getAuctionid()).get();
        Notification toOwner = this.saveAndSendNotifications(auction, true);
        Notification toHighestBidder = this.saveAndSendNotifications(auction, false);
        if(toOwner != null){
            try{
                socketService.sendToOne(toOwner.getUser().getEmail(), new SocketWrapper(toOwner), SocketWrapper.class);
            }catch (Exception e){}
        }
        if(toHighestBidder != null){
            try{
                socketService.sendToOne(toHighestBidder.getUser().getEmail(), new SocketWrapper(toHighestBidder), SocketWrapper.class);
            }catch (Exception e){}
        }
    }

    private Notification saveAndSendNotifications(Auctions auction, boolean owner){
        Users user = null;
        Notification n = null;
        try{
            user = owner ? auction.getUsers() : auction.getBids().get(1).getUser();
        } catch (Exception e){}
        if(user != null){
            n = new Notification();
            n.setAuction(auction);
            n.setAuctionid(auction.getAuctionid());
            n.setBid(auction.getBids().get(0));
            n.setBidid(n.getBid().getId());
            n.setUser(user);
            n.setUserid(user.getUserid());
            notificationRepo.save(n);
        }
        return n;
    }

    public void readMyNotifications(String email){
        Users user = userRepo.findDistinctFirstByEmailIgnoreCase(email);
        List<Notification> notificationList = (List<Notification>) notificationRepo.findAllByUseridAndHasread(user.getUserid(), (short) 0);
        for (Notification notification : notificationList){
            notification.setHasread((short) 1);
            notificationRepo.save(notification);
        }
    }

    public Iterable<Notification> getAllMyNotifications(String email){
        Users user = userRepo.findDistinctFirstByEmailIgnoreCase(email);
        return notificationRepo.findAllByUseridAndHasread(user.getUserid(), (short) 0);
    }
}
