package com.worldsbestauctions.auctionsite.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Bid {

    @Id
    private int userid;
    private int auctionid;
    private double bidamount;
    private Date bidtime;

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


}
