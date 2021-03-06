package com.worldsbestauctions.auctionsite.services;

import com.worldsbestauctions.auctionsite.entities.SecretUser;
import com.worldsbestauctions.auctionsite.repos.SecretUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecretUserService {

    @Autowired
    SecretUserRepo secretUserRepo;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public BCryptPasswordEncoder getEncoder() {
        return encoder;
    }


    public SecretUser getSecretUserByEmail(String email) {
        return secretUserRepo.findDistinctFirstByEmailIgnoreCase(email);
    }

    public SecretUser save(SecretUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return secretUserRepo.save(user);
    }
}
