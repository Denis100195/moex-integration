package com.stock.analysis.moex.integration.service;

import com.stock.analysis.moex.integration.dto.Security;
import org.junit.Test;
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
public class MoexDataServiceTest {

    @Autowired
    private MoexDataService moexDataService;

    @org.junit.Test
    public void testReturnSecurity(){
        List<Security> securityList = moexDataService.getSecurityDataOnDate(LocalDate.of(2020, 12, 28));
        System.out.println(securityList);
    }
    @org.junit.Test
    public void testReturnOneSec(){
        Security oneSec = moexDataService.getOneSecurityByNameOnDate(LocalDate.of(2020,3,20), "AFLT");
        System.out.println(oneSec.toString());
    }

    @Test
    public void testSaveSecOnDate() throws Exception {
        moexDataService.saveSecuritiesOnDate(LocalDate.of(2020,12,29));
    }

}