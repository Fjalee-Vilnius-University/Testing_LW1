package com.HelloWebApp.HelloWebApp.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.AssertTrue;

class SaskaitaTest {
    private Saskaita saskaita;

    @BeforeEach
    public void setup(){
        saskaita = new Saskaita();
    }

    @Test
    public void constructor_id_telNrId_menuo_suma_telNr_userId_WorksCorrectly(){
       var id = 1;
       var telNrId = 2;
       var menuo = 2;
       var suma = 45;
       var telNr = "+370656765";
       var saskaita = new Saskaita(id, telNrId, menuo, suma, telNr);

        assertAll(
                "constructor works corerctly",
                () -> assertEquals(id, saskaita.getId()),
                () -> assertEquals(telNrId, saskaita.getTelNrId()),
                () -> assertEquals(menuo, saskaita.getMenuo()),
                () -> assertEquals(suma, saskaita.getSuma()),
                () -> assertEquals(telNr, saskaita.getTelNr())
        );
    }

    @Test
    public void constructor_telNrId_menuo_suma_telNr_userId_WorksCorrectly(){
        var telNrId = 2;
        var menuo = 2;
        var suma = 45;
        var telNr = "+370656765";
        var saskaita = new Saskaita(telNrId, menuo, suma, telNr);

        assertAll(
                "constructor works corerctly",
                () -> assertEquals(telNrId, saskaita.getTelNrId()),
                () -> assertEquals(menuo, saskaita.getMenuo()),
                () -> assertEquals(suma, saskaita.getSuma()),
                () -> assertEquals(telNr, saskaita.getTelNr())
        );
    }

    @Test
    public void setNr_menuoBelowOne_throws(){
        assertThrows(
            IllegalArgumentException.class,
            () -> saskaita.setMenuo(0)
        );
    }

    @Test
    public void setNr_menuoAboveTwelve_throws(){
        assertThrows(
            IllegalArgumentException.class,
            () -> saskaita.setMenuo(13)
        );
    }

    @Test
    public void setNr_menuoValid_throws(){
        assertAll(
                "Menuo is valid",
                () -> saskaita.setMenuo(1),
                () -> saskaita.setMenuo(12),
                () -> saskaita.setMenuo(8)
        );
    }
}