package com.worldsbestauctions.auctionsite.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Image {

    private int auctionId;
    private String path;

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
