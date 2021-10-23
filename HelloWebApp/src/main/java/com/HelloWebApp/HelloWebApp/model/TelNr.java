package com.HelloWebApp.HelloWebApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TelNr {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String telNr;
    private int userId;

    protected TelNr() {
    }

    public TelNr(String telNr, int userId) {
        this.telNr = telNr;
        this.userId = userId;
    }

    public String getTelNr() {
        return telNr;
    }

    public void setTelNr(String telNr) {
        this.telNr = telNr;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return id + " " + userId + "-user " + telNr;
    }
}
