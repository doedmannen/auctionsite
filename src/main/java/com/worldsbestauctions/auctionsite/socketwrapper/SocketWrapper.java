package com.worldsbestauctions.auctionsite.socketwrapper;

public class SocketWrapper {
    Sendable msgObject;
    String type;
    public SocketWrapper(Sendable msgObject){
        this.msgObject = msgObject; 
        this.type = msgObject.getClass().getSimpleName();
    }
}
