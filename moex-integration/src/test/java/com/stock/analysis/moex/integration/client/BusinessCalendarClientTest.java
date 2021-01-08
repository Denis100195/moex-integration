package com.stock.analysis.moex.integration.client;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BusinessCalendarClientTest {

    @Autowired
    BusinessCalendarClient businessCalendarClient;
    @Test
    public void getPreviousWorkingDate() {
        LocalDate previousWorkingDate = businessCalendarClient.getPreviousWorkingDate(LocalDate.of(2020, 12, 22), "RUS");
        Assert.assertEquals(LocalDate.of(2020, 12, 21), previousWorkingDate);
    }
}