package com.stock.analysis.moex.integration.service;

import com.stock.analysis.moex.integration.dto.Security;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MoexDataServiceTest {
    @Autowired
    private MoexDataService moexDataService;
    @org.junit.Test
    public void testParseDoc() throws Exception {
        List<Security> securityList = moexDataService.parseDoc(LocalDate.of(2020, 3, 20));
        System.out.println();
    }
//    @org.junit.Test
//    public void testPutSecurity(){
//        moexDataService.putSecurity(LocalDate.of(2020, 3, 20));
//    }
//    @org.junit.Test
//    public void testReturnSecurity(){
//        List<Security> securityList = moexDataService.returnSecurity(LocalDate.of(2020, 3, 20));
//        System.out.println(securityList);
//    }
//    @org.junit.Test
//    public void testReturnOneSec(){
//        Security oneSec = moexDataService.getOneSecurity(LocalDate.of(2020,3,20), "AFLT");
//        System.out.println(oneSec.toString());
//    }
}