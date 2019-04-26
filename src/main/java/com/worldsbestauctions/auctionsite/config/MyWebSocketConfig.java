package com.worldsbestauctions.auctionsite.config;

import com.worldsbestauctions.auctionsite.controllers.SocketController;
import com.worldsbestauctions.auctionsite.services.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class MyWebSocketConfig implements WebSocketConfigurer {

    @Autowired
    SocketService socketService;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        SocketController sc = new SocketController();
        sc.setSocketService(socketService);
        registry.addHandler(sc, "/websocketa").setAllowedOrigins("http://localhost:8081");
    }
}

