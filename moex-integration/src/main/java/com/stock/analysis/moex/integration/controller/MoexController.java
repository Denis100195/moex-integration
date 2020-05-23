package com.stock.analysis.moex.integration.controller;

import com.stock.analysis.moex.integration.dto.Security;
import com.stock.analysis.moex.integration.service.MoexDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class MoexController {

    private final MoexDataService moexDataService;

    public MoexController(MoexDataService moexDataService){
        this.moexDataService = moexDataService;
    }

    @GetMapping(value = "/getAllSecOnDate")
    public List<Security> getAllSecOnDate (@RequestParam(name = "date")LocalDate date){
        return moexDataService.getSecurityDataOnDate(date);
    }

    @GetMapping(value = "/getOneSecByNameOnDate")
    public Security getOneSecByNameOnDate(@RequestParam(name = "date")LocalDate date,
                                          @RequestParam(name = "secName")String secName){
        return moexDataService.getOneSecurityByNameOnDate(date, secName);
    }
}
