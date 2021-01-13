package com.stock.analysis.moex.integration.client;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

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
        businessCalendarClient.isDateWorkingDay(LocalDate.of(2020, 12, 25), RUSSIA_CODE);
    }

    @Test
    public void getWorkingDaysBetweenDates(){
        businessCalendarClient.getWorkingDaysBetweenDates(
                LocalDate.of(2020, 11, 25),
                LocalDate.of(2020, 12, 10),
                RUSSIA_CODE
        );
    }
    @Test
    public void getListOfWDBetweenDatesTest(){
        businessCalendarClient.getListOfWDBetweenDates(
                LocalDate.of(2020, 11, 25),
                LocalDate.of(2020, 12, 10),
                RUSSIA_CODE
        );
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