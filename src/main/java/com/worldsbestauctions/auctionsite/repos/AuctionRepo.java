package com.worldsbestauctions.auctionsite.repos;


import com.worldsbestauctions.auctionsite.entities.Auctions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepo extends CrudRepository<Auctions, Long> {

}
