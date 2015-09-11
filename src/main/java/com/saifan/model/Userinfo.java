package com.saifan.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by ma on 2015/9/9.
 */
@Entity
public class Userinfo implements Serializable {
    private String userid;
    private String name;
    private String portraituri;
    private String token;

    @Id
    @Column(name = "USERID")
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Userinfo userinfo = (Userinfo) o;

        if (userid != null ? !userid.equals(userinfo.userid) : userinfo.userid != null) return false;
        if (name != null ? !name.equals(userinfo.name) : userinfo.name != null) return false;
        if (portraituri != null ? !portraituri.equals(userinfo.portraituri) : userinfo.portraituri != null)
            return false;
        if (token != null ? !token.equals(userinfo.token) : userinfo.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid != null ? userid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (portraituri != null ? portraituri.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }
}
