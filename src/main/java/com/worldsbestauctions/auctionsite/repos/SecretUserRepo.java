package com.worldsbestauctions.auctionsite.repos;

import com.worldsbestauctions.auctionsite.entities.SecretUser;
import com.worldsbestauctions.auctionsite.entities.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretUserRepo extends CrudRepository<SecretUser, Long> {
    SecretUser findDistinctFirstByEmailIgnoreCase(String email);
}
