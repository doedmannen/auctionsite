package com.worldsbestauctions.auctionsite.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
public class Bids {

    @Id
    private int userid;
    private int auctionid;
    private double bidamount;
    private LocalDateTime bidtime;

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getUserid() {
        return userid;
    }

    public void setAuctionid(int auctionid) {
        this.auctionid = auctionid;
    }

    public int getAuctionid() {
        return auctionid;
    }

    public void setBidamount(double bidamount) {
        this.bidamount = bidamount;
    }

    public double getBidamount() {
        return bidamount;
    }

    public void setBidtime(LocalDateTime bidtime) {
        this.bidtime = bidtime;
    }

    public LocalDateTime getBidtime() {
        return bidtime;
    }
}
