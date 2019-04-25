package com.worldsbestauctions.auctionsite.controllers;

import com.worldsbestauctions.auctionsite.entities.SecretUser;
import com.worldsbestauctions.auctionsite.entities.Users;
import com.worldsbestauctions.auctionsite.services.SecretUserService;
import com.worldsbestauctions.auctionsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SecretUserService secretUserService;

    @GetMapping("/whoami")
    Users whoAmI(HttpServletRequest request){
        System.out.println("**********************************************hejehej");
        Users u = null;
        try{
            u = userService.getUserByEmail(request.getUserPrincipal().getName());
        }catch (Exception e){}
        return u;
    }
    @GetMapping("/getall")
    Iterable getUsers(){
        System.out.println("------------------------------------------------------------------------------------hejhejehje");
        return userService.findAll();
}

    @PostMapping
    void createNewUser(@RequestBody SecretUser body) {
        secretUserService.save(body);
    }
}
