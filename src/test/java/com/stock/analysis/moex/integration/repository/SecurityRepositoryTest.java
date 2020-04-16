package com.stock.analysis.moex.integration.repository;

import com.stock.analysis.moex.integration.dto.Security;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SecurityRepositoryTest {
    @Autowired
    private SecurityRepository securityRepository;

    @Test
    public void testGetRows() {
        List<Security> securityList = securityRepository.findAll();
        Assert.assertFalse(securityList.isEmpty());
    }

    @Test
    public void testInsRow(){
        securityRepository.insRow("TQBR",
                LocalDate.parse("2020-03-20"),
                "АбрауДюрсо",
                "ABRD",
                new BigDecimal("156.0"),
                new BigDecimal("475165.0"),
                new BigDecimal("120.5"),
                new BigDecimal("116.5"),
                new BigDecimal("129.5"),
                new BigDecimal("125.5"),
                new BigDecimal("124.0"),
                new BigDecimal("124.0"),
                new BigDecimal("3830.0"),
                new BigDecimal("0.0"),
                new BigDecimal("124.0"),
                new BigDecimal("125.5"),
                new BigDecimal("0.0"),
                new BigDecimal("503060"),
                new BigDecimal("0.0"),
                new BigDecimal("0.0"));
    }
}