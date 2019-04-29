package com.worldsbestauctions.auctionsite.controllers;

import com.google.gson.Gson;
import com.worldsbestauctions.auctionsite.entities.Message;
import com.worldsbestauctions.auctionsite.entities.Users;
import com.worldsbestauctions.auctionsite.services.MessageService;
import com.worldsbestauctions.auctionsite.services.SocketService;
import com.worldsbestauctions.auctionsite.services.UserService;
import com.worldsbestauctions.auctionsite.socketwrapper.SocketWrapper;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Controller
public class SocketController extends TextWebSocketHandler {

    private MessageService messageService;
    private SocketService socketService;
    public void setSocketService(SocketService socketService) {
        this.socketService = socketService;
    }
    public void setMessageService(MessageService messageService){this.messageService = messageService; }
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        for (int i = 0; i < 30; i++) System.out.println("I GOT A MESSAGE");
        messageService.incommingChatMessage(session, new Gson().fromJson(message.getPayload(), Message.class));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        socketService.addSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        socketService.removeSession(session);
    }
}