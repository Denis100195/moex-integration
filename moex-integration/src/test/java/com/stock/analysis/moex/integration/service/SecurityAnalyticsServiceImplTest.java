package com.stock.analysis.moex.integration.service;

import com.stock.analysis.moex.integration.dto.Security;
import com.stock.analysis.moex.integration.dto.SecurityPriceDifference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SecurityAnalyticsServiceImplTest {

    @Autowired
    private SecurityAnalyticsServiceImpl securityAnalyticsServiceImpl;

    @org.junit.Test
    public void addSecOnPrevWorkDay() {
    }

    @org.junit.Test
    public void getTopSecurityAtTimeTest() throws Exception {
        List<SecurityPriceDifference> topSecurityAtTime = securityAnalyticsServiceImpl.getTopSecurityAtTime
                (LocalDate.of(2020, 12, 28), LocalDate.of(2020, 12, 29), 10, true);
        System.out.println(topSecurityAtTime);

    }
    @org.junit.Test
    public void getOrderedSecurity(){
        Map<Integer, List<Security>> myMap = securityAnalyticsServiceImpl.getOrderedSecOnNmTr();
        for(Map.Entry<Integer, List<Security>> entry: myMap.entrySet()) {
            // get key
            Integer key = entry.getKey();
            // get value
            List<Security> listSec = entry.getValue();
            System.out.println(key + " : " + listSec.toString());
        }
    }
}