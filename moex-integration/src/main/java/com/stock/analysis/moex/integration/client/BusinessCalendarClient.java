package com.stock.analysis.moex.integration.client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class BusinessCalendarClient {

    @Value("${host}")
    private String host;

    public LocalDate getNextWorkingDate(LocalDate date,String country){
        final String getNextWD = host + "/getNextWorkingDate?" +
                "InDate=" + date.toString() +
                "&Country=" + country;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity <LocalDate> result = restTemplate.getForEntity(getNextWD, LocalDate.class);
        return result.getBody();
    }
    public LocalDate getPreviousWorkingDate(LocalDate date, String country){
        final String getPrevWD = host + "/getPreviousWorkingDate?" +
                "InDate=" + date.toString() +
                "&Country=" + country;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LocalDate> entity = restTemplate.getForEntity(getPrevWD, LocalDate.class);
        return entity.getBody();
    }

    public Boolean isDateWorkingDay(LocalDate date, String country){
        final String isDateWD = host + "/isDateWorkingDay?" +
                "InDate=" + date.toString() +
                "&Country=" + country;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> result = restTemplate.getForEntity(isDateWD, Boolean.class);
        return result.getBody();
    }
    public Long getWorkingDaysBetweenDates(LocalDate beginDate, LocalDate endDate, String country){
        final String getWDBD = host + "/getWorkingDaysBetweenDates?" +
                "BeginDate=" + beginDate.toString() +
                "&EndDate=" + endDate.toString() +
                "&Country=" + country;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity <Long> result = restTemplate.getForEntity(getWDBD, Long.class);
        return result.getBody();

    }
    public List<LocalDate> getListOfWDBetweenDates(LocalDate beginDate, LocalDate endDate, String country){
        final String getListOfWDBD = host + "/getListOfWorkingDaysBetweenDates?" +
                "BeginDate=" + beginDate.toString() +
                "&EndDate=" + endDate.toString() +
                "&Country=" + country;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity <LocalDate[]> result = restTemplate.getForEntity(getListOfWDBD, LocalDate[].class);
        return Arrays.asList(result.getBody());
    }
    public void addDaysToDate(LocalDate inDate, String country, boolean withHolidays, int count){
        final String addDToD = host + "/addDaysToDate?" + 7 +
                "InDate=" + inDate.toString() +
                "&Country=" + country +
                "&withHolidays=" + withHolidays +
                "&count=" + count;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForEntity(addDToD, String.class).toString();
        System.out.println(result);
    }

}
