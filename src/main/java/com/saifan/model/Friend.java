package com.saifan.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by ma on 2015/9/9.
 */
@Entity
public class Friend {
    private int id;
    private String myuserid;
    private String name;
    private String portraituri;
    private String token;
    private String userid;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MYUSERID")
    public String getMyuserid() {
        return myuserid;
    }

    public void setMyuserid(String myuserid) {
        this.myuserid = myuserid;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "PORTRAITURI")
    public String getPortraituri() {
        return portraituri;
    }

    public void setPortraituri(String portraituri) {
        this.portraituri = portraituri;
    }

    @Basic
    @Column(name = "TOKEN")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "USERID")
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Friend friend = (Friend) o;

        if (id != friend.id) return false;
        if (myuserid != null ? !myuserid.equals(friend.myuserid) : friend.myuserid != null) return false;
        if (name != null ? !name.equals(friend.name) : friend.name != null) return false;
        if (portraituri != null ? !portraituri.equals(friend.portraituri) : friend.portraituri != null) return false;
        if (token != null ? !token.equals(friend.token) : friend.token != null) return false;
        if (userid != null ? !userid.equals(friend.userid) : friend.userid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (myuserid != null ? myuserid.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (portraituri != null ? portraituri.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        return result;
    }
}
