package com.worldsbestauctions.auctionsite.repos;

import com.worldsbestauctions.auctionsite.entities.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<Users, Long> {
    Users findDistinctFirstByEmailIgnoreCase(String email);
}
