package com.worldsbestauctions.auctionsite.controllers;

import com.worldsbestauctions.auctionsite.entities.Notification;
import com.worldsbestauctions.auctionsite.entities.Users;
import com.worldsbestauctions.auctionsite.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("/read")
    void readAll(HttpServletRequest request){
        try{
            notificationService.readMyNotifications(request.getUserPrincipal().getName());
        } catch (Exception e){}
    }
    @GetMapping
    List<Notification> getAll(HttpServletRequest request){
        List<Notification> notificationList = null;
        try{
            notificationList = (List<Notification>) notificationService.getAllMyNotifications(request.getUserPrincipal().getName());
        } catch (Exception e){}
        return notificationList.stream().sorted(Comparator.comparing(Notification::getId).reversed()).collect(Collectors.toList());
    }
}
