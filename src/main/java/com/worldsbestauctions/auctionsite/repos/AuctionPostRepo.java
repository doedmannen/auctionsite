package com.worldsbestauctions.auctionsite.repos;


import com.worldsbestauctions.auctionsite.entities.AuctionPost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionPostRepo extends CrudRepository<AuctionPost, Long> {

}
