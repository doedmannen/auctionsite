package com.worldsbestauctions.auctionsite.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Images {

    private int auctionid;
    @Id
    private String path;

    public void setAuctionid(int auctionid) {
        this.auctionid = auctionid;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
