package com.worldsbestauctions.auctionsite.services;

import com.worldsbestauctions.auctionsite.entities.Images;
import com.worldsbestauctions.auctionsite.repos.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    ImageRepo imageRepo;

    public Images save(Images s){
        return imageRepo.save(s);
    }
}
