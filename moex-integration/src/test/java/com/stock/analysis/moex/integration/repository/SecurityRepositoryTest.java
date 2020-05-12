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

//    @Test
//    public void testGetRows() {
//        List<Security> securityList = securityRepository.findAllByDate(LocalDate.of(2020, 1, 16));
//        Assert.assertFalse(securityList.isEmpty());
//    }


}