package com.worldsbestauctions.auctionsite.repos;

import com.worldsbestauctions.auctionsite.entities.AuctionPost;
import com.worldsbestauctions.auctionsite.entities.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends CrudRepository<Image, Long> {

}
