package com.worldsbestauctions.auctionsite.repos;

import com.worldsbestauctions.auctionsite.entities.Images;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends CrudRepository<Images, Long> {

}
