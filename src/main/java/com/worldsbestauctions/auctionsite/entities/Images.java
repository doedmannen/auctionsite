package com.worldsbestauctions.auctionsite.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Images {

    private long auctionid;
    @Id
    private String path;

    public Images(){}
    public Images(long auctionid, String path){
        this.auctionid = auctionid;
        this.path = path;
    }

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
