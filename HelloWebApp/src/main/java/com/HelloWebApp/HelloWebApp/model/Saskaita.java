package com.HelloWebApp.HelloWebApp.model;

import com.HelloWebApp.HelloWebApp.service.TelNrService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Saskaita {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int telNrId;
    private int menuo;
    private int suma;
    private String telNr;

    public Saskaita() {
    }

    public Saskaita(int id, int telNrId, int menuo, int suma, String telNr) {
        this.id = id;
        this.telNrId = telNrId;
        this.menuo = menuo;
        this.suma = suma;
        this.telNr = telNr;
    }

    public Saskaita(int telNrId, int menuo, int suma, String telNr) {
        this.telNrId = telNrId;
        this.menuo = menuo;
        this.suma = suma;
        this.telNr = telNr;
    }

    @Override
    public String toString(){
        return id + " " + telNrId + "-telNrId " + " " + menuo + "menesi skolingas " + suma + "euru";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelNrId() {
        return telNrId;
    }

    public void setTelNrId(int telNrId) {
        this.telNrId = telNrId;
    }

    public int getMenuo() {
        return menuo;
    }

    public void setMenuo(int menuo) {
        this.menuo = menuo;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public String getTelNr() {
        return telNr;
    }

    public void setTelNr(String telNr) {
        this.telNr = telNr;
    }
}
