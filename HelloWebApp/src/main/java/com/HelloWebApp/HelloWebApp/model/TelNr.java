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
    private String nr;
    private int userId;

    public TelNr() {}

    public TelNr(String nr, int userId) {
        this.nr = nr;
        this.userId = userId;
    }

    public TelNr(int id, String nr, int userId) {
        this.nr = nr;
        this.userId = userId;
        this.id = id;
    }

    @Override
    public String toString(){
        return id + " " + userId + "-user " + nr;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
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
}
