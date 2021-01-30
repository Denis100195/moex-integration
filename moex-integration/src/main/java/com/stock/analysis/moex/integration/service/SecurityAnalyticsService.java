package com.stock.analysis.moex.integration.service;

import com.stock.analysis.moex.integration.client.BusinessCalendarClient;
import com.stock.analysis.moex.integration.dto.Security;
import com.stock.analysis.moex.integration.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.stock.analysis.moex.integration.config.Constants.RUSSIA_CODE;

@Service
public class SecurityAnalyticsService {
    private BusinessCalendarClient businessCalendarClient;
    private MoexDataService moexDataService;
    private SecurityRepository securityRepository;

    @Autowired
    public SecurityAnalyticsService(
            BusinessCalendarClient businessCalendarClient, MoexDataService moexDataService, SecurityRepository securityRepository){
        this.businessCalendarClient = businessCalendarClient;
        this.moexDataService = moexDataService;
        this.securityRepository = securityRepository;
    }
    //метод сохраняет даныые в БД за предыдущий рабочий день по расписанию
    @Scheduled(cron = "${rates.loader.cron}")
    public void addSecOnPrevWorkDay() throws Exception{
        LocalDate workDay = businessCalendarClient.getPreviousWorkingDate(LocalDate.now(), RUSSIA_CODE);
        moexDataService.saveSecuritiesOnDate(workDay);
    }

    //топ акций, которые за определенное время выросли и упали
    public List<Security> getTopSecurityAtTime(LocalDate beginDate, LocalDate endDate, int topCount, boolean isIncrease){

        private String ORDER_SECURITY_BY_PRICE_DIFFERENCE = "select s1.sec_id, s1.short_name, ((s2.close/s1.close-1)*100) dif from security s1\n" +
                "inner join security s2 on s1.sec_id=s2.sec_id\n" +
                "where s1.trade_date='2020-12-28' and s2.trade_date='2020-12-29' and s1.close is not null and  s2.close is not null\n" +
                "order by dif"
    }




}
