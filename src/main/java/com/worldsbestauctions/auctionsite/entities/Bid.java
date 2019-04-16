package com.worldsbestauctions.auctionsite.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Bid {

    @Id
    private int userId;
    private int auctionId;
    private double bidAmount;
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


}
