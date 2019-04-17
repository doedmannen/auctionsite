package com.worldsbestauctions.auctionsite.repos;


import com.worldsbestauctions.auctionsite.entities.Auctions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepo extends PagingAndSortingRepository<Auctions, Long> {
    Iterable<Auctions> findTop10ByOrderByAuctionidDesc();
}