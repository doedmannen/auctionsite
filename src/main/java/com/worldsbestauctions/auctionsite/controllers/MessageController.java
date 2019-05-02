package com.worldsbestauctions.auctionsite.controllers;

import com.worldsbestauctions.auctionsite.entities.Message;
import com.worldsbestauctions.auctionsite.entities.Users;
import com.worldsbestauctions.auctionsite.repos.MessageRepo;
import com.worldsbestauctions.auctionsite.repos.UserRepo;
import com.worldsbestauctions.auctionsite.services.MessageService;
import com.worldsbestauctions.auctionsite.services.SocketService;
import com.worldsbestauctions.auctionsite.socketwrapper.SocketWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    UserRepo userRepo;
    @Autowired
    MessageRepo messageRepo;
    @Autowired
    SocketService socketService;

    @GetMapping("/read/{id}")
    void readAllMessages(@PathVariable long id, HttpServletRequest request){
        Users receiver = null;
        Users sender = null;
        List<Message> messageList;
        try{
            receiver = userRepo.findDistinctFirstByEmailIgnoreCase(request.getUserPrincipal().getName());
            sender = userRepo.findById(id).get();
        }catch(Exception e){}
        if(receiver != null){
            messageList = (List<Message>) messageRepo.findMessageBySenderidAndReceiveridAndHasread(sender.getUserid(), receiver.getUserid(), (short)0);
            for (Message message : messageList){
                message.setHasread((short)1);
                messageRepo.save(message);
//                Intended use was: Resend to receiver and sender to confirm read on both ends. But I don't have time for the full
//                implementation
//                try{
//                    socketService.sendToOne(receiver.getEmail(), new SocketWrapper(message), SocketWrapper.class);
//                }catch (Exception e){}
//                try{
//                    socketService.sendToOne(sender.getEmail(), new SocketWrapper(message), SocketWrapper.class);
//                }catch (Exception e){}
            }
        }
    }

    @GetMapping
    List<Message> getAllMyMessages(HttpServletRequest request){
        Users user = null;
        List<Message> history = null;
        try{
            user = userRepo.findDistinctFirstByEmailIgnoreCase(request.getUserPrincipal().getName());
            history = (List<Message>) messageRepo.findMessageBySenderidOrReceiverid(user.getUserid(), user.getUserid());
        } catch (Exception e){}
        return history;
    }
}
