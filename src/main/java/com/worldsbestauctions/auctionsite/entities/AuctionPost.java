package com.worldsbestauctions.auctionsite.entities;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
public class AuctionPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int auctionOwner;
    private String title;
    private String description;
    private int category;
    private double startPrice;
    private Date startTime;
    private Date endTime;

    public long getId() {
        return id;
    }

    public void setAuctionOwner(int auctionOwner) {
        this.auctionOwner = auctionOwner;
    }

    public int getAuctionOwner() {
        return auctionOwner;
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

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public double getStartPrice() {
        return startPrice;
    }


}
