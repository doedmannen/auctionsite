package com.worldsbestauctions.auctionsite.entities;


import com.worldsbestauctions.auctionsite.socketwrapper.Sendable;

import java.time.LocalDateTime;

public class Message implements Sendable {
    long id;
    long senderid;
    long receiverid;
    String message;
    LocalDateTime timesent;
    public Message(String message){
        this.id = 1;
        this.senderid = 1;
        this.receiverid = 2;
        this.message = message;
        this.timesent = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSenderid() {
        return senderid;
    }

    public void setSenderid(long senderid) {
        this.senderid = senderid;
    }

    public long getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(long receiverid) {
        this.receiverid = receiverid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimesent() {
        return timesent;
    }

    public void setTimesent(LocalDateTime timesent) {
        this.timesent = timesent;
    }
}
