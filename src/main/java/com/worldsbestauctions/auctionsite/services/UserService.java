package com.worldsbestauctions.auctionsite.services;

import com.worldsbestauctions.auctionsite.entities.Users;
import com.worldsbestauctions.auctionsite.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public BCryptPasswordEncoder getEncoder() {
        return encoder;
    }

    @Autowired
    UserRepo userRepo;

    public Users getUserByEmail(String email) {
        return userRepo.findDistinctFirstByEmailIgnoreCase(email);
    }

    public void save(Users body) {
        Users user = new Users(body.getEmail(), body.getFirstname(), body.getLastname(), encoder.encode(body.getPassword()));
        try {
            userRepo.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

