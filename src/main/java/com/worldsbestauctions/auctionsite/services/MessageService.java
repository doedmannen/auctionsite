package com.worldsbestauctions.auctionsite.services;

import com.worldsbestauctions.auctionsite.entities.Message;
import com.worldsbestauctions.auctionsite.entities.Users;
import com.worldsbestauctions.auctionsite.repos.MessageRepo;
import com.worldsbestauctions.auctionsite.repos.UserRepo;
import com.worldsbestauctions.auctionsite.socketwrapper.MessageHistory;
import com.worldsbestauctions.auctionsite.socketwrapper.SocketWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;


@Service
public class MessageService {

    @Autowired
    MessageRepo messageRepo;
    @Autowired
    SocketService socketService;
    @Autowired
    UserRepo userRepo;

    public void incommingChatMessage(WebSocketSession session, Message m){
        Users sender, receiver;
        long id;
        try{
            sender = userRepo.findDistinctFirstByEmailIgnoreCase(session.getPrincipal().getName());
            receiver = userRepo.findById(m.getReceiverid()).get();
            m.setSenderid(sender.getUserid());
            m.setSender(sender);
            m.setReceiver(receiver);
            id = messageRepo.save(m).getId();
            m.setId(id);
            socketService.sendToOne(m.getSender().getEmail(), new SocketWrapper(m), SocketWrapper.class);
            socketService.sendToOne(m.getReceiver().getEmail(), new SocketWrapper(m), SocketWrapper.class);
        }catch (Exception e){}
    }

    public void broadcastMessageHistory(WebSocketSession session){
        try{
            Users user = userRepo.findDistinctFirstByEmailIgnoreCase(session.getPrincipal().getName());
            Iterable<Message> history = messageRepo.findMessageBySenderidOrReceiverid(user.getUserid(), user.getUserid());
            socketService.sendToOne(session, new SocketWrapper(new MessageHistory((List) history)), SocketWrapper.class);
        } catch (Exception e){}
    }
}
