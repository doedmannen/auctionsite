package com.worldsbestauctions.auctionsite.entities;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Date;

@Entity
public class Bid {

    private int userId;
    private int auctionId;
    private double bidAmount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date bidTime;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidTime(Date bidTime) {
        this.bidTime = bidTime;
    }

    public Date getBidTime() {
        return bidTime;
    }
}
