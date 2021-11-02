
package com.HelloWebApp.HelloWebApp.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.AssertTrue;

class TelNrTest {
    private TelNr telNr;
    private String telNrNoPrefix = "1111111";

    @BeforeEach
    public void setup(){
        telNr = new TelNr();
    }

    @Test
    public void constructor_nr_userId_WorksCorrectly(){
        var nr = "867656765";
        var userId = 2;
        var telNr = new TelNr(nr, userId);

        assertAll(
                "constructor works corerctly",
                () -> assertEquals(nr, telNr.getNr()),
                () -> assertEquals(userId, telNr.getUserId())
        );
    }

    @Test
    public void constructor_id_nr_userIdid_WorksCorrectly(){
        var id = 3;
        var nr = "867656765";
        var userId = 2;
        var telNr = new TelNr(id, nr, userId);

        assertAll(
                "constructor works corerctly",
                () -> assertEquals(id, telNr.getId()),
                () -> assertEquals(nr, telNr.getNr()),
                () -> assertEquals(userId, telNr.getUserId())
        );
    }

    @Test
    public void setNr_invalidPrefix_throws(){
        var validNumber = "97" + telNrNoPrefix;

        assertThrows(
            IllegalArgumentException.class,
            () -> telNr.setNr(validNumber)
        );
    }

    @Test
    public void setNr_validLongPrefix_numberTooLong_throws(){
        var validNumber = "+3706" + telNrNoPrefix + "1";

        assertThrows(
                IllegalArgumentException.class,
                () -> telNr.setNr(validNumber)
        );
    }

    @Test
    public void setNr_validShortPrefix_numberTooLong_throws(){
        var validNumber = "86" + telNrNoPrefix + "1";

        assertThrows(
                IllegalArgumentException.class,
                () -> telNr.setNr(validNumber)
        );
    }

    @Test
    public void setNr_validLongPrefix_numberTooShort_throws(){
        var validNumber = "+3706" + StringUtils.chop(telNrNoPrefix);

        assertThrows(
                IllegalArgumentException.class,
                () -> telNr.setNr(validNumber)
        );
    }

    @Test
    public void setNr_validShortPrefix_numberTooShort_throws(){
        var validNumber = "86" + StringUtils.chop(telNrNoPrefix);

        assertThrows(
                IllegalArgumentException.class,
                () -> telNr.setNr(validNumber)
        );
    }

    @Test
    public void setNr_shortPrefix_correct(){
        var validNumber = "86" + telNrNoPrefix;

        telNr.setNr(validNumber);

        assertEquals(validNumber, telNr.getNr());
    }

    @Test
    public void setNr_longPrefix_correct(){
        var validNumber = "+3706" + telNrNoPrefix;

        telNr.setNr(validNumber);

        assertEquals(validNumber, telNr.getNr());
    }
}