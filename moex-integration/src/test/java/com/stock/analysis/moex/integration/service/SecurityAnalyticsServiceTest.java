package com.stock.analysis.moex.integration.service;

import com.stock.analysis.moex.integration.dto.SecurityPriceDifference;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SecurityAnalyticsServiceTest {

    @Autowired
    private SecurityAnalyticsService securityAnalyticsService;

    @org.junit.Test
    public void addSecOnPrevWorkDay() {
    }

    @org.junit.Test
    public void getTopSecurityAtTimeTest() throws Exception {
        List<SecurityPriceDifference> topSecurityAtTime = securityAnalyticsService.getTopSecurityAtTime
                (LocalDate.of(2020, 12, 28), LocalDate.of(2020, 12, 29), 10, true);
        System.out.println(topSecurityAtTime);

    }
}