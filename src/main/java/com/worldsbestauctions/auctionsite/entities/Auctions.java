package com.worldsbestauctions.auctionsite.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Auctions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long auctionid;
    private long auctionowner;
    private String title;
    private String description;
    private int category;
    private double startprice;
    private LocalDateTime starttime;
    private LocalDateTime endtime;

    @ManyToOne
    @JoinColumn(name = "auctionowner", referencedColumnName = "userid", insertable = false, updatable = false)
    private Users users;

    public Users getUsers() {
        return users;
    }

    @OneToMany(mappedBy = "auctionid")
    private List<Bids> bids;

    public List<Bids> getBids() {
        return bids.stream().sorted(Comparator.comparing(Bids::getBidamount).reversed()).collect(Collectors.toList());
    }

    @OneToMany(mappedBy = "auctionid")
    private List<Images> images;

    public String[] getImages() {
        return images.stream().sorted(Comparator.comparing(Images::getOrder)).map(Images::getPath).toArray(String[]::new);
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

    public void setStarttime(LocalDateTime starttime) {
        this.starttime = starttime;
    }

    public LocalDateTime getStarttime() {
        return starttime;
    }

    public void setEndtime(LocalDateTime endtime) {
        this.endtime = endtime;
    }

    public LocalDateTime getEndtime() {
        return endtime;
    }

    public void setAuctionowner(long auctionowner) {
        this.auctionowner = auctionowner;
    }

    public long getAuctionowner() {
        return auctionowner;
    }
}
