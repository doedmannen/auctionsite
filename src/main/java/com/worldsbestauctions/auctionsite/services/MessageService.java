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

import java.time.LocalDateTime;
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
        Users sender = null;
        Users receiver = null;
        System.out.println(session.getPrincipal().getName());
        try{
            sender = userRepo.findDistinctFirstByEmailIgnoreCase(session.getPrincipal().getName());
            receiver = userRepo.findById(m.getReceiverid()).get();
        }catch (Exception e){}

        for (int i = 0; i < 30; i++) System.out.println(sender.getEmail());

        if(sender != null){
            if(sender != null && receiver != null){
                this.saveAndSend(sender, receiver, m);
            }else {
                if(m.getMessage().equals("handshake")){
                    socketService.addLoggedIn(session);
                    for (int i = 0; i < 30; i++)
                        System.out.println("USER IS NOW AUTH");
                }
            }
        }
    }

    public void saveAndSend(Users sender, Users receiver, Message m){
        long id;
        m.setSenderid(sender.getUserid());
        m.setSender(sender);
        m.setReceiver(receiver);
        m.setTimesent(LocalDateTime.now());
        id = messageRepo.save(m).getId();
        m.setId(id);
        try{
            socketService.sendToOne(m.getSender().getEmail(), new SocketWrapper(m), SocketWrapper.class);
        }catch (Exception e){}
        try{
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
