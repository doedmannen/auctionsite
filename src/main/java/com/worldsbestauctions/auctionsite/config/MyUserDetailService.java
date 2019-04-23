package com.worldsbestauctions.auctionsite.config;

import com.worldsbestauctions.auctionsite.entities.SecretUser;
import com.worldsbestauctions.auctionsite.entities.Users;
import com.worldsbestauctions.auctionsite.repos.SecretUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@Configuration
public class MyUserDetailService implements UserDetailsService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public BCryptPasswordEncoder getEncoder() {
        return encoder;
    }

    @Autowired
    SecretUserRepo secretUserRepo;

    @PostConstruct
    private void createDefaultUser(){
        if(secretUserRepo.findDistinctFirstByEmailIgnoreCase("something@something.com") == null){
            addUser("something@something.com", "Kenneth", "Boman", "password123");
        }
    }

    public void addUser(String email, String firstname, String lastname, String password){
        if(secretUserRepo.findDistinctFirstByEmailIgnoreCase(email) == null){
            SecretUser secretUser = new SecretUser(email, firstname, lastname, encoder.encode(password));
            try{
                secretUserRepo.save(secretUser);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private UserDetails toUserDetails(SecretUser secretUser){
        return org.springframework.security.core.userdetails.User
                .withUsername(secretUser.getEmail())
                .password(secretUser.getPassword())
                .roles("USER").build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SecretUser secretUser = secretUserRepo.findDistinctFirstByEmailIgnoreCase(email);
        if(secretUser == null){
            throw new UsernameNotFoundException("User not found with email " + email);
        }
        return toUserDetails(secretUser);
    }
}
