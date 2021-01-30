package com.stock.analysis.moex.integration.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class FunctionServiceTest {
    @Autowired
    private SecurityAnalyticsService functionService;

    @Test
    public void testGetTopSecurityAtTime() throws Exception{
        functionService.getTopSecurityAtTime(
                LocalDate.of(2020, 12, 28),
                LocalDate.of(2020, 12, 29),
                10, true);
    }
}