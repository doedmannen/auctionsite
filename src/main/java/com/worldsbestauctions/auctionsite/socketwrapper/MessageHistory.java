package com.worldsbestauctions.auctionsite.socketwrapper;

import com.worldsbestauctions.auctionsite.entities.Message;

import java.util.List;

public class MessageHistory implements Sendable {
    private List<Message> history;

    public MessageHistory(List<Message> history){
        this.history = history;
    }

    public List<Message> getHistory() {
        return history;
    }
}
