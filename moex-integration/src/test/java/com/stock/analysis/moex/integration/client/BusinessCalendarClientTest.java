package com.stock.analysis.moex.integration.client;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static com.stock.analysis.moex.integration.config.Constants.RUSSIA_CODE;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class BusinessCalendarClientTest {

    @Autowired
    BusinessCalendarClient businessCalendarClient;
    @Test
    public void getPreviousWorkingDateTest() {
        LocalDate previousWorkingDate = businessCalendarClient.getPreviousWorkingDate(LocalDate.of(2020, 12, 22), RUSSIA_CODE);
        Assert.assertEquals(LocalDate.of(2020, 12, 21), previousWorkingDate);
        Assert.assertNotEquals(LocalDate.of(2020,5,9), previousWorkingDate);
    }
    @Test
    public void getGetNextWorkingDateTest(){
        LocalDate nextWorkingDate = businessCalendarClient.getNextWorkingDate(LocalDate.of(2020, 12, 27), RUSSIA_CODE);
        Assert.assertEquals(LocalDate.of(2020, 12, 28), nextWorkingDate);
    }
    @Test
    public void isDateWorkingDateTest(){
        Boolean dateWorkingDay = businessCalendarClient.isDateWorkingDay(LocalDate.of(2020, 12, 26), RUSSIA_CODE);
        Assert.assertEquals(false, dateWorkingDay);
    }

    @Test
    public void getWorkingDaysBetweenDates(){
        Long workingDaysBetweenDates = businessCalendarClient.getWorkingDaysBetweenDates(
                LocalDate.of(2020, 12, 9),
                LocalDate.of(2020, 12, 11),
                RUSSIA_CODE
        );
        Assert.assertEquals(Long.valueOf(3), workingDaysBetweenDates);
    }
    @Test
    public void getListOfWDBetweenDatesTest(){
        List<LocalDate> listOfWDBetweenDates = businessCalendarClient.getListOfWDBetweenDates(
                LocalDate.of(2020, 11, 25),
                LocalDate.of(2020, 12, 10),
                RUSSIA_CODE
        );
        Assert.assertEquals(12, listOfWDBetweenDates.size());
    }
    @Test
    public void addDaysToDateTest(){
        businessCalendarClient.addDaysToDate(
                LocalDate.of(2020, 12, 10),
                RUSSIA_CODE,
                false,
                10
        );
    }


}