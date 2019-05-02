package com.worldsbestauctions.auctionsite.services;

import com.worldsbestauctions.auctionsite.entities.SecretUser;
import com.worldsbestauctions.auctionsite.entities.Users;
import com.worldsbestauctions.auctionsite.repos.SecretUserRepo;
import com.worldsbestauctions.auctionsite.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public Users getUserByEmail(String email) {
        return userRepo.findDistinctFirstByEmailIgnoreCase(email);
    }
    public Optional<Users> findById(long id){
        return userRepo.findById(id);
    }

    public Users getUserById(long id) {
        return userRepo.findById(id).get();
    }

    public Users updateIcon(Users body) {
       return userRepo.save(body);
    }

}

