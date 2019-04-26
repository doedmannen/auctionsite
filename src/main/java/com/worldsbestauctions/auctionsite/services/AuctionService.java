package com.worldsbestauctions.auctionsite.services;

import com.worldsbestauctions.auctionsite.entities.Auctions;
import com.worldsbestauctions.auctionsite.entities.Images;
import com.worldsbestauctions.auctionsite.repos.AuctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuctionService {

    @Autowired
    AuctionRepo auctionPostRepo;

    @Autowired
    ImageService imageService;

    @Autowired
    UserService userService;

    public Iterable search (String value){
        return auctionPostRepo.findDistinctByTitleContainingOrDescriptionContaining(value, value);
    }

    public Iterable getAll(){
        return auctionPostRepo.findAllByOrderByEndtime();
    }


    public long create(String email, Auctions body) {
        body.setAuctionowner(userService.getUserByEmail(email).getUserid());
        body.setStarttime(LocalDateTime.now());
        body.setEndtime(LocalDateTime.now().plusDays(2));
        long id = auctionPostRepo.save(body).getAuctionid();
        byte order = 0;
        for(String path : body.getImages()){
            imageService.save(new Images(id, path, ++order));
        }
        return id;
    }
}
