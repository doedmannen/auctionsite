package com.worldsbestauctions.auctionsite.entities;

import com.worldsbestauctions.auctionsite.socketwrapper.Sendable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
public class Bids implements Sendable {


    private  long userid;
    private int auctionid;
    private double bidamount;
    @Id
    private LocalDateTime bidtime;

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getUserid() {
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
