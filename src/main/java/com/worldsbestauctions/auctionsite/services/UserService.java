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

    public Users getMockUser(){
        List<Users> userlist = (List) userRepo.findAll();
        return userlist.get(0);
    }
}
