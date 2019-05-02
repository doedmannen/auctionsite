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
    private String avatar_class;
    private String avatar_color;

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

    public String getAvatar_class() {
        return avatar_class;
    }

    public void setAvatar_class(String avatar_class) {
        this.avatar_class = avatar_class;
    }

    public String getAvatar_color() {
        return avatar_color;
    }

    public void setAvatar_color(String avatar_color) {
        this.avatar_color = avatar_color;
    }
}
