package com.worldsbestauctions.auctionsite.controllers;

import com.google.gson.Gson;
import com.worldsbestauctions.auctionsite.entities.Message;
import com.worldsbestauctions.auctionsite.services.SocketService;
import com.worldsbestauctions.auctionsite.services.UserService;
import com.worldsbestauctions.auctionsite.socketwrapper.SocketWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Controller
public class SocketController extends TextWebSocketHandler {

    private SocketService socketService;
    public void setSocketService(SocketService socketService) {
        this.socketService = socketService;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        socketService.handleMessage(session, new Gson().fromJson(message.getPayload(), Message.class));

        // Example with a generic Map instead of converting the JSON to a specific class
        // Map keysAndValues = new Gson().fromJson(message.getPayload(), Map.class);
        // Get the value of a key named "firstname"
        // String firstname = keysAndValues.get("firstname");
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