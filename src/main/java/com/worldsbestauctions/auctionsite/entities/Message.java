package com.worldsbestauctions.auctionsite.entities;


import com.worldsbestauctions.auctionsite.socketwrapper.Sendable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Messages")
public class Message implements Sendable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    long senderid;
    long receiverid;
    String message;
    LocalDateTime timesent;

    @ManyToOne
    @JoinColumn(name="senderid", referencedColumnName = "userid", insertable = false, updatable = false)
    private Users sender;

    @ManyToOne
    @JoinColumn(name="receiverid", referencedColumnName = "userid", insertable = false, updatable = false)
    private Users receiver;

    public Message(){}

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

    public Users getSender() {
        return sender;
    }

    public void setSender(Users sender) {
        this.sender = sender;
    }

    public Users getReceiver() {
        return receiver;
    }

    public void setReceiver(Users receiver) {
        this.receiver = receiver;
    }
}
