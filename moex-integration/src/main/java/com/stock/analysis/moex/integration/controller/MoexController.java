package com.stock.analysis.moex.integration.controller;

import com.stock.analysis.moex.integration.dto.Security;
import com.stock.analysis.moex.integration.dto.SecurityPriceDifference;
import com.stock.analysis.moex.integration.service.MoexDataServiceImpl;
import com.stock.analysis.moex.integration.service.SecurityAnalyticsServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class MoexController {

    private final MoexDataServiceImpl moexDataServiceImpl;
    private final SecurityAnalyticsServiceImpl securityAnalyticsServiceImpl;

    public MoexController(MoexDataServiceImpl moexDataServiceImpl, SecurityAnalyticsServiceImpl securityAnalyticsServiceImpl){
        this.moexDataServiceImpl = moexDataServiceImpl;
        this.securityAnalyticsServiceImpl = securityAnalyticsServiceImpl;
    }

    @GetMapping(value = "/security")
    public List<Security> getAllSecOnDate (@RequestParam(name = "date")String date){
        return moexDataServiceImpl.getSecurityDataOnDate(LocalDate.parse(date));
    }

    @GetMapping(value = "/getOneSecByNameOnDate")
    public Security getOneSecByNameOnDate(@RequestParam(name = "date")String date,
                                          @RequestParam(name = "secName")String secName){
        return moexDataServiceImpl.getOneSecurityByNameOnDate(LocalDate.parse(date), secName);
    }

    @GetMapping(value = "/topSecuritiesAtTime")
    public List<SecurityPriceDifference> getSecurityPriceDifferences(@RequestParam(name = "beginDate") String beginDate,
                                                                     @RequestParam(name = "endDate") String endDate,
                                                                     @RequestParam(name = "count") int count,
                                                                     @RequestParam(name = "isIncrease") boolean isIncrease){
        return securityAnalyticsServiceImpl.getTopSecurityAtTime(LocalDate.parse(beginDate), LocalDate.parse(endDate), count, isIncrease);
    }
}
