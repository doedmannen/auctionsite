package com.worldsbestauctions.auctionsite.controllers;


import com.worldsbestauctions.auctionsite.entities.Users;
import com.worldsbestauctions.auctionsite.repos.UserRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/login")
public class LoginController {

    UserRepo userRepo;

    @PostMapping
    Users newUser(@RequestBody Users newUser) {
        return userRepo.save(newUser);
    }

}
