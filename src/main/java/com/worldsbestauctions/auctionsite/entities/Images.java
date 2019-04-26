package com.worldsbestauctions.auctionsite.entities;

import javax.persistence.*;

@Entity
public class Images {

    private long auctionid;
    @Id
    private String path;
    @Column(name = "img_order")
    private int order;
    public Images(){}
    public Images(long auctionid, String path, byte order){
        this.auctionid = auctionid;
        this.path = path;
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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
