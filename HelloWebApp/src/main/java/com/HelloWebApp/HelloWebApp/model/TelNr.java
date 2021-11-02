package com.HelloWebApp.HelloWebApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.regex.Pattern;

@Entity
public class TelNr {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nr;
    private int userId;

    public TelNr() {}

    public TelNr(String nr, int userId) {
        setNr(nr);
        this.userId = userId;
    }

    public TelNr(int id, String nr, int userId) {
        setNr(nr);
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
        Pattern telNrLongPattern = Pattern.compile("^\\+3706[0-9]{7}$");
        Pattern telNrShortPattern = Pattern.compile("^86[0-9]{7}$");
        if (telNrLongPattern.matcher(nr).matches() || telNrShortPattern.matcher(nr).matches()){
            this.nr = nr;
        }
        else{
            throw new IllegalArgumentException();
        }
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
