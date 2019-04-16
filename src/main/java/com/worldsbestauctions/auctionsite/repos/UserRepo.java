package com.worldsbestauctions.auctionsite.repos;

import com.worldsbestauctions.auctionsite.entities.Image;
import com.worldsbestauctions.auctionsite.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
}
