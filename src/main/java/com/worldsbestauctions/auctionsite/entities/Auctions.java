package com.worldsbestauctions.auctionsite.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Auctions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long auctionid;
   // private int auctionowner;
    private String title;
    private String description;
    private int category;
    private double startprice;
    private Date starttime;
    private Date endtime;

    @ManyToOne
    @JoinColumn(name="auctionowner")
    private Users users;

    public Users getUsers() {
        return users;
    }

    public void setAuctionid(long auctionid) {
        this.auctionid = auctionid;
    }

    public long getAuctionid() {
        return auctionid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }

    public void setStartprice(double startprice) {
        this.startprice = startprice;
    }

    public double getStartprice() {
        return startprice;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Date getEndtime() {
        return endtime;
    }


}
