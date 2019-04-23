package com.worldsbestauctions.auctionsite.services;

import com.worldsbestauctions.auctionsite.entities.Users;
import com.worldsbestauctions.auctionsite.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public Users getUserByEmail(String email){
        return userRepo.findDistinctFirstByEmailIgnoreCase(email);
    }
    public Users save(Users u){
        return userRepo.save(u);
    }
}
