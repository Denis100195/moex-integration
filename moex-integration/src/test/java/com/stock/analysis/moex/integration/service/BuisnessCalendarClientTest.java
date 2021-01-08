package com.stock.analysis.moex.integration.service;

import com.stock.analysis.moex.integration.client.BusinessCalendarClient;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BuisnessCalendarClientTest {
    @Autowired
    private BusinessCalendarClient buisnessCalendarClient;

    @org.junit.Test
    public void getNextWorkingDateTest(){
        buisnessCalendarClient.getNextWorkingDate("2019-11-15", "RUS");
    }

}
