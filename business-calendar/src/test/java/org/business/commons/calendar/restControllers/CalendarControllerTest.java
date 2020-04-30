package org.business.commons.calendar.restControllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by User on 9/2/2017.
 */



@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class CalendarControllerTest {

    private static final Logger log = LoggerFactory.getLogger(CalendarControllerTest.class);

    @Autowired
    CalendarController calendarController;

    @Test
    public void getNextWorkingDate() throws Exception {

        String nextWorkingDateInRussia = calendarController.getNextWorkingDate("01.09.2017", "Russia");
        assertEquals("04.09.2017", nextWorkingDateInRussia);

        String prevWorkingDateInRussia = calendarController.getPreviousWorkingDate("12.06.2017", "Russia");
        assertEquals("09.06.2017", prevWorkingDateInRussia);



        String prevWorkingDateInLux = calendarController.getPreviousWorkingDate("26.05.2017", "Luxembourg");
        assertEquals("25.05.2017", prevWorkingDateInLux);

        String prevWorkingDateInLux1 = calendarController.getPreviousWorkingDate("02.01.2018", "Luxembourg");
        assertEquals("29.12.2017", prevWorkingDateInLux1);



    }


    @Test
    public void isDateWorkingDay() throws Exception {

        boolean workingDay = calendarController.isDateWorkingDay("16.09.2017", "Luxembourg");
        assertFalse(workingDay);


        boolean workingDay1 = calendarController.isDateWorkingDay("09.05.2017", "Russia");
        assertFalse(workingDay1);


        boolean workingDay2 = calendarController.isDateWorkingDay("14.09.2017", "Russia");
        assertTrue(workingDay2);

    }




}