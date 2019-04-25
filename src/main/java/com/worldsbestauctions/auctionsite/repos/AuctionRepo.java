package com.worldsbestauctions.auctionsite.repos;


import com.worldsbestauctions.auctionsite.entities.Auctions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;

@Repository
public interface AuctionRepo extends CrudRepository<Auctions, Long> {
    Iterable<Auctions> findTop10ByOrderByEndtimeAsc();

   /* Iterable<Auctions> findByEndtimeGreaterThanOrderByEndtimeDesc(LocalDateTime endTime);*/
}
