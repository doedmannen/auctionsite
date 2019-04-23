package com.worldsbestauctions.auctionsite.entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;
    private String email;
    private String firstname;
    private String lastname;

    public Users(){}

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getUserid() {
        return userid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }


}
