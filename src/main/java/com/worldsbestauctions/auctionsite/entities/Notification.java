package com.worldsbestauctions.auctionsite.entities;

import com.worldsbestauctions.auctionsite.socketwrapper.Sendable;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification implements Sendable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userid;
    private long auctionid;
    private long bidid;
    private short hasread;

    @ManyToOne
    @JoinColumn(name="senderid", referencedColumnName = "userid", insertable = false, updatable = false)
    private Users user;
    @ManyToOne
    @JoinColumn(name="auctionid", referencedColumnName = "auctionid", insertable = false, updatable = false)
    private Auctions auction;
    @ManyToOne
    @JoinColumn(name="bidid", referencedColumnName = "id", insertable = false, updatable = false)
    private Bids bid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(long auctionid) {
        this.auctionid = auctionid;
    }

    public long getBidid() {
        return bidid;
    }

    public void setBidid(long bidid) {
        this.bidid = bidid;
    }

    public short getHasread() {
        return hasread;
    }

    public void setHasread(short hasread) {
        this.hasread = hasread;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Auctions getAuction() {
        return auction;
    }

    public void setAuction(Auctions auction) {
        this.auction = auction;
    }

    public Bids getBid() {
        return bid;
    }

    public void setBid(Bids bid) {
        this.bid = bid;
    }
}
