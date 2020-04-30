package org.businessday.api.calendar.controller;

import org.businessday.api.calendar.exception.YearNotSupportedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by User on 9/2/2017.
 */


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class CalendarControllerTest {

    @Autowired
    CalendarController calendarController;

    @Test
    public void getNextWorkingDate() throws YearNotSupportedException {

        LocalDate nextWorkingDateInRussia = calendarController.getNextWorkingDate("2019-11-15", "RUS");
        assertEquals(LocalDate.parse("2019-11-18"), nextWorkingDateInRussia);

        LocalDate prevWorkingDateInRussia = calendarController.getPreviousWorkingDate("2017-06-12", "RUS");
        assertEquals(LocalDate.parse("2017-06-09"), prevWorkingDateInRussia);

        LocalDate prevWorkingDateInLux = calendarController.getPreviousWorkingDate("2017-05-26", "LUX");
        assertEquals(LocalDate.parse("2017-05-25"), prevWorkingDateInLux);

        LocalDate prevWorkingDateInLux1 = calendarController.getPreviousWorkingDate("2018-01-02", "LUX");
        assertEquals(LocalDate.parse("2017-12-29"), prevWorkingDateInLux1);


    }


    @Test
    public void isDateWorkingDay() {

        boolean workingDay = calendarController.isDateWorkingDay("2017-09-16", "LUX");
        assertFalse(workingDay);

        boolean workingDay1 = calendarController.isDateWorkingDay("2017-05-09", "RUS");
        assertFalse(workingDay1);

        boolean workingDay2 = calendarController.isDateWorkingDay("2017-09-14", "RUS");

        assertTrue(workingDay2);
        boolean workingDay3 = calendarController.isDateWorkingDay("2018-09-14", "USA");
        assertTrue(workingDay3);


    }


}