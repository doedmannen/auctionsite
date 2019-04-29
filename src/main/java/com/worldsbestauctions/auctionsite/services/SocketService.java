package com.worldsbestauctions.auctionsite.services;

import com.google.gson.Gson;
import com.worldsbestauctions.auctionsite.entities.Message;
import com.worldsbestauctions.auctionsite.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.net.http.WebSocket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SocketService {

    private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private ConcurrentHashMap<String, HashSet<WebSocketSession>> loggedIn = new ConcurrentHashMap<>();


    public void sendToOne(WebSocketSession webSocketSession, String message) throws IOException {
        webSocketSession.sendMessage(new TextMessage(message));
    }

    public void sendToOne(WebSocketSession webSocketSession, Object obj, Class klass) throws IOException {
        sendToOne(webSocketSession, new Gson().toJson(obj, klass));
    }

    public void sendToOne(String email, Object obj, Class klass) throws IOException {
        System.out.println("Sending msg to one user");
        if(loggedIn.containsKey(email)) {
            System.out.println(loggedIn.get(email).size());
            loggedIn.get(email).forEach(session -> {
                try{
                    this.sendToOne(session, obj, klass);
                }catch (Exception e){ e.printStackTrace();}
            });
        }
    }


    public void sendToAll(Object obj, Class klass) {
        sendToAll(new Gson().toJson(obj, klass));
    }

    public void sendToAll(String message) {
        TextMessage msg = new TextMessage(message);
        for(WebSocketSession webSocketSession : sessions) {
            try {
                webSocketSession.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addSession(WebSocketSession session) {
        sessions.add(session);
        addLoggedIn(session);
    }

    public void addLoggedIn(WebSocketSession session){
        try{
            if(!loggedIn.containsKey(session.getPrincipal().getName())){
                loggedIn.put(session.getPrincipal().getName(), new HashSet<>());
            }
            loggedIn.get(session.getPrincipal().getName()).add(session);
        } catch (Exception e) {e.printStackTrace();}
    }

    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
        removeLoggedIn(session);
    }

    public void removeLoggedIn(WebSocketSession session){
        try{
            loggedIn.get(session.getPrincipal().getName()).remove(session);
            if(loggedIn.get(session.getPrincipal().getName()).size() == 0){
                loggedIn.remove(session.getPrincipal().getName());
            }
        } catch (Exception e) {e.printStackTrace();}
    }
}
