package com.stock.analysis.moex.integration.controller;

import com.stock.analysis.moex.integration.dto.Security;
import com.stock.analysis.moex.integration.dto.SecurityPriceDifference;
import com.stock.analysis.moex.integration.service.MoexDataService;
import com.stock.analysis.moex.integration.service.SecurityAnalyticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class MoexController {

    private final MoexDataService moexDataService;
    private final SecurityAnalyticsService securityAnalyticsService;

    public MoexController(MoexDataService moexDataService, SecurityAnalyticsService securityAnalyticsService){
        this.moexDataService = moexDataService;
        this.securityAnalyticsService = securityAnalyticsService;
    }

    @GetMapping(value = "/security")
    public List<Security> getAllSecOnDate (@RequestParam(name = "date")String date){
        return moexDataService.getSecurityDataOnDate(LocalDate.parse(date));
    }

    @GetMapping(value = "/getOneSecByNameOnDate")
    public Security getOneSecByNameOnDate(@RequestParam(name = "date")String date,
                                          @RequestParam(name = "secName")String secName){
        return moexDataService.getOneSecurityByNameOnDate(LocalDate.parse(date), secName);
    }

    @GetMapping(value = "/topSecuritiesAtTime")
    public List<SecurityPriceDifference> getSecurityPriceDifferences(@RequestParam(name = "beginDate") String beginDate,
                                                                     @RequestParam(name = "endDate") String endDate,
                                                                     @RequestParam(name = "count") int count,
                                                                     @RequestParam(name = "isIncrease") boolean isIncrease){
        return securityAnalyticsService.getTopSecurityAtTime(LocalDate.parse(beginDate), LocalDate.parse(endDate), count, isIncrease);
    }
}
