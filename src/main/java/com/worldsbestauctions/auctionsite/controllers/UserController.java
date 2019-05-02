package com.worldsbestauctions.auctionsite.controllers;

import com.worldsbestauctions.auctionsite.entities.SecretUser;
import com.worldsbestauctions.auctionsite.entities.Users;
import com.worldsbestauctions.auctionsite.services.SecretUserService;
import com.worldsbestauctions.auctionsite.services.SocketService;
import com.worldsbestauctions.auctionsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SecretUserService secretUserService;

    @Autowired
    SocketService socketService;

    @GetMapping("/whoami")
    Users whoAmI(HttpServletRequest request){
        Users u = null;
        try{
            u = userService.getUserByEmail(request.getUserPrincipal().getName());
        }catch (Exception e){}
        return u;
    }
    @PostMapping
    long createNewUser(@RequestBody SecretUser body) {
        long id = 0;
        if(secretUserService.getSecretUserByEmail(body.getEmail()) == null)
            id = secretUserService.save(body).getUserid();
        return id;
    }
    @GetMapping("/profile/{userid}")
    Users sendUserToFront(@PathVariable long userid) {
        return userService.getUserById(userid);
    }

    @PutMapping
    Users updateIcon(@RequestBody Users body, HttpServletRequest request) {
        Users user = userService.getUserByEmail(request.getUserPrincipal().getName());
        user.setAvatar_class(body.getAvatar_class());
        return userService.updateIcon(user);
    }

    @GetMapping("/online")
    List<String> getOnlineUsers() {
        return socketService.getLoggedInUsers();
    }
}
