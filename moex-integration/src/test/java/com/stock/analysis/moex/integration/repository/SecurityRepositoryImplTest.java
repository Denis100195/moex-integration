package com.stock.analysis.moex.integration.repository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SecurityRepositoryImplTest {
    @Autowired
    private SecurityRepositoryImpl securityRepositoryImpl;

//    @Test
//    public void testGetRows() {
//        List<Security> securityList = securityRepository.findAllByDate(LocalDate.of(2020, 1, 16));
//        Assert.assertFalse(securityList.isEmpty());
//    }


}