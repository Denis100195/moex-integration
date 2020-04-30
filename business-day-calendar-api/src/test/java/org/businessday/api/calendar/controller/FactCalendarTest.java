package org.businessday.api.calendar.controller;

import lombok.extern.slf4j.Slf4j;
import org.businessday.api.calendar.exception.YearNotSupportedException;
import org.businessday.api.calendar.service.BusinessDayCalendarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by User on 9/2/2017.
 */


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Slf4j
public class FactCalendarTest {

    @Autowired
    private BusinessDayCalendarService factCalendar;


    @Test
    public void getNextWorkingDate() throws YearNotSupportedException {
        LocalDate nextWorkingDate = factCalendar.getNextWorkingDate(LocalDate.parse("2017-09-02"), "RUS");
        assertEquals("2017-09-04", nextWorkingDate.toString());

        LocalDate nextWorkingDate0101 = factCalendar.getNextWorkingDate(LocalDate.parse("2017-01-01"), "RUS");
        assertEquals("2017-01-09", nextWorkingDate0101.toString());

        LocalDate nextWorkingDateLux = factCalendar.getNextWorkingDate(LocalDate.parse("2017-10-31"), "LUX");
        assertEquals("2017-11-02", nextWorkingDateLux.toString());

        LocalDate nextWorkingDate3108 = factCalendar.getNextWorkingDate(LocalDate.parse("2017-08-31"), "RUS");
        assertEquals("2017-09-01", nextWorkingDate3108.toString());

    }


    @Test
    public void isHoliday() {
        assertTrue(factCalendar.isHoliday(LocalDate.parse("2017-09-02"), "RUS"));
        assertFalse(factCalendar.isHoliday(LocalDate.parse("2017-09-04"), "RUS"));
        assertTrue(factCalendar.isHoliday(LocalDate.parse("2017-06-12"), "RUS"));
        assertTrue(factCalendar.isHoliday(LocalDate.parse("2019-03-08"), "RUS"));
    }


    @Test
    public void getPreviousWorkingDate() throws Exception {
        LocalDate nextPreviousDate = factCalendar.getPreviousWorkingDate(LocalDate.parse("2017-09-10"), "RUS");
        assertEquals("2017-09-08", nextPreviousDate.toString());

        LocalDate nextPreviousDateNY = factCalendar.getPreviousWorkingDate(LocalDate.parse("2017-01-08"), "RUS");
        assertEquals("2016-12-30", nextPreviousDateNY.toString());

        LocalDate nextPreviousDateMAY = factCalendar.getPreviousWorkingDate(LocalDate.parse("2017-05-09"), "RUS");
        assertEquals("2017-05-05", nextPreviousDateMAY.toString());
    }


    @Test
    public void getDatesBetween() {
        List<LocalDate> datesBetween = factCalendar.getDatesBetweenInclusive(LocalDate.now().minusDays(5), LocalDate.now());
        assertEquals(6, datesBetween.size());

    }


    @Test
    public void getWorkingDaysBetweenDates() throws Exception {
        long workingDaysBetweenDates = factCalendar.getCountOfWorkingDaysBetweenDates(LocalDate.parse("2017-04-28"), LocalDate.parse("2017-05-02"), "RUS");
        log.info(String.valueOf(workingDaysBetweenDates));
        assertEquals(2, workingDaysBetweenDates);

        long workingDaysBetweenWeelendDates = factCalendar.getCountOfWorkingDaysBetweenDates(LocalDate.parse("2017-09-16"), LocalDate.parse("2017-09-17"), "RUS");
        assertEquals(0, workingDaysBetweenWeelendDates);

        long workingDaysBetweenNyDates = factCalendar.getCountOfWorkingDaysBetweenDates(LocalDate.parse("2017-01-01"), LocalDate.parse("2017-01-05"), "RUS");
        assertEquals(0, workingDaysBetweenNyDates);

    }

    @Test
    public void addDaysToDate() throws YearNotSupportedException {
        LocalDate resultWithHolidays = factCalendar.addDaysToDate(LocalDate.parse("2019-12-26"), "RUS", true, 3);
        assertEquals(LocalDate.parse("2019-12-29"), resultWithHolidays);

        LocalDate resultWithoutHolidays = factCalendar.addDaysToDate(LocalDate.parse("2019-12-26"), "RUS", false, 3);
        assertEquals(LocalDate.parse("2019-12-31"), resultWithoutHolidays);

        LocalDate resultWithHolidays2 = factCalendar.addDaysToDate(LocalDate.parse("2019-12-26"), "RUS", true, 10);
        assertEquals(LocalDate.parse("2020-01-05"), resultWithHolidays2);

        LocalDate resultWithoutHolidays2 = factCalendar.addDaysToDate(LocalDate.parse("2019-12-26"), "RUS", false, 10);
        assertEquals(LocalDate.parse("2020-01-17"), resultWithoutHolidays2);

        LocalDate resultWithoutHolidays3 = factCalendar.addDaysToDate(LocalDate.parse("2019-12-27"), "RUS", false, 1);
        assertEquals(LocalDate.parse("2019-12-30"), resultWithoutHolidays3);

        LocalDate resultWithoutHolidays4 = factCalendar.addDaysToDate(LocalDate.parse("2020-01-01"), "RUS", false, 1);
        assertEquals(LocalDate.parse("2020-01-09"), resultWithoutHolidays4);

        LocalDate resultWithoutHolidays5 = factCalendar.addDaysToDate(LocalDate.parse("2020-01-01"), "RUS", false, 2);
        assertEquals(LocalDate.parse("2020-01-10"), resultWithoutHolidays5);
    }


    @Test
    public void getWorkingDatesBetweenDates() throws YearNotSupportedException {
        List<LocalDate> usaWorkingDates = factCalendar.getWorkingDaysBetweenDates(LocalDate.of(2020, 2, 21),
                LocalDate.of(2020, 2, 25), "USA");
        assertEquals(3, usaWorkingDates.size());
    }

    @Test
    public void simplUnitTest() {
        log.info("Ололо!");
    }


}