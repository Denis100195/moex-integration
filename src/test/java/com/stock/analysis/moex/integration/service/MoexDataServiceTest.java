package com.stock.analysis.moex.integration.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MoexDataServiceTest {
    @Autowired
    private MoexDataService moexDataService;
    @org.junit.Test
    public void testParseDoc() throws Exception {
        moexDataService.parseDoc(LocalDate.of(2020,3,20));
    }
}