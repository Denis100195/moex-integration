package org.business.commons.calendar.restControllers;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by User on 9/2/2017.
 */


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class FactCalendarTest {


    private static final Logger log = LoggerFactory.getLogger(FactCalendarTest.class);

    @Autowired
    FactCalendar factCalendar;


    @Test
    public void getNextWorkingDate() throws Exception {
        Date nextWorkingDate = factCalendar.getNextWorkingDate(DateUtils.parseDate("02.09.2017", "dd.MM.yyyy"), "Russia");
        assertEquals("04.09.2017", DateFormatUtils.format(nextWorkingDate, "dd.MM.yyyy"));


        Date nextWorkingDate0101 = factCalendar.getNextWorkingDate(DateUtils.parseDate("01.01.2017", "dd.MM.yyyy"), "Russia");
        log.info(DateFormatUtils.format(nextWorkingDate0101, "dd.MM.yyyy"));
        assertEquals("09.01.2017", DateFormatUtils.format(nextWorkingDate0101, "dd.MM.yyyy"));


        Date nextWorkingDateLux = factCalendar.getNextWorkingDate(DateUtils.parseDate("31.10.2017", "dd.MM.yyyy"), "Luxembourg");
        log.info(DateFormatUtils.format(nextWorkingDateLux, "dd.MM.yyyy"));
        assertEquals("02.11.2017", DateFormatUtils.format(nextWorkingDateLux, "dd.MM.yyyy"));

        Date nextWorkingDateNYRussia = factCalendar.getNextWorkingDate(DateUtils.parseDate("31.12.2016", "dd.MM.yyyy"), "Russia");
        log.info(DateFormatUtils.format(nextWorkingDateNYRussia, "dd.MM.yyyy"));
        assertEquals("09.01.2017", DateFormatUtils.format(nextWorkingDateNYRussia, "dd.MM.yyyy"));


        Date nextWorkingDate3108 = factCalendar.getNextWorkingDate(DateUtils.parseDate("31.08.2017", "dd.MM.yyyy"), "Russia");
        log.info(DateFormatUtils.format(nextWorkingDate3108, "dd.MM.yyyy"));
        assertEquals("01.09.2017", DateFormatUtils.format(nextWorkingDate3108, "dd.MM.yyyy"));


    }



    @Test
    public void isHoliday() throws Exception {
        assertTrue(factCalendar.isHoliday(DateUtils.parseDate("02.09.2017", "dd.MM.yyyy"), "Russia"));
        assertFalse(factCalendar.isHoliday(DateUtils.parseDate("04.09.2017", "dd.MM.yyyy"), "Russia"));
        assertTrue(factCalendar.isHoliday(DateUtils.parseDate("12.06.2017", "dd.MM.yyyy"), "Russia"));
        assertTrue(factCalendar.isHoliday(DateUtils.parseDate("08.03.2019", "dd.MM.yyyy"), "Russia"));
    }




    @Test
    public void getPreviousWorkingDate() throws Exception {
        Date nextPreviousDate = factCalendar.getPreviousWorkingDate(DateUtils.parseDate("10.09.2017", "dd.MM.yyyy"), "Russia");
        log.info(DateFormatUtils.format(nextPreviousDate, "dd.MM.yyyy"));
        assertEquals("08.09.2017", DateFormatUtils.format(nextPreviousDate, "dd.MM.yyyy"));

        Date nextPreviousDateNY = factCalendar.getPreviousWorkingDate(DateUtils.parseDate("08.01.2017", "dd.MM.yyyy"), "Russia");
        log.info(DateFormatUtils.format(nextPreviousDateNY, "dd.MM.yyyy"));
        assertEquals("30.12.2016", DateFormatUtils.format(nextPreviousDateNY, "dd.MM.yyyy"));


        Date nextPreviousDateMAY = factCalendar.getPreviousWorkingDate(DateUtils.parseDate("09.05.2017", "dd.MM.yyyy"), "Russia");
        log.info(DateFormatUtils.format(nextPreviousDateMAY, "dd.MM.yyyy"));
        assertEquals("05.05.2017", DateFormatUtils.format(nextPreviousDateMAY, "dd.MM.yyyy"));

    }


    @Test
    public void getDatesBetween() throws Exception {
        List<Date> datesBetween = factCalendar.getDatesBetweenInclusive(DateUtils.addDays(new Date(), -5), new Date());

        for (int i = 0; i < datesBetween.size(); i++) {
            log.info(DateFormatUtils.format(datesBetween.get(i), "dd.MM.yyyy"));

        }
    }



    @Test
    public void getWorkingDaysBetweenDates() throws Exception {
        int workingDaysBetweenDates = factCalendar.getWorkingDaysBetweenDates("28.04.2017", "02.05.2017", "Russia");
        log.info(String.valueOf(workingDaysBetweenDates));
        assertEquals(2, workingDaysBetweenDates);

        int workingDaysBetweenWeelendDates = factCalendar.getWorkingDaysBetweenDates("16.09.2017", "17.09.2017", "Russia");
        assertEquals(0, workingDaysBetweenWeelendDates);

        int workingDaysBetweenNyDates = factCalendar.getWorkingDaysBetweenDates("01.01.2017", "5.01.2017", "Russia");
        assertEquals(0, workingDaysBetweenNyDates);

    }


    @Test
    public void simplUnitTest() {
        log.info("Ололо!");
    }



}