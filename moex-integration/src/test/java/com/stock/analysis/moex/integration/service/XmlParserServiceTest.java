package com.stock.analysis.moex.integration.service;

import com.stock.analysis.moex.integration.dto.Security;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class XmlParserServiceTest {
    @Autowired
    XmlParserService xmlParserService;

    @org.junit.Test
    public void testParseDoc() throws Exception {
        List<Security> securityList = xmlParserService.getSecuritiesOnDateFromMoex(LocalDate.of(2020, 3, 20));
        System.out.println(securityList);
    }
}