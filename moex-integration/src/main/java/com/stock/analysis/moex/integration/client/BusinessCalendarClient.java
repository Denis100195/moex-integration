package com.stock.analysis.moex.integration.client;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;
import java.time.LocalDate;

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
    public void getWorkingDaysBetweenDates(LocalDate beginDate, LocalDate endDate, String country){
        final String getWDBD = host + "/getWorkingDaysBetweenDates?" +
                "BeginDate=" + beginDate.toString() +
                "&EndDate=" + endDate.toString() +
                "&Country=" + country;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(getWDBD, String.class);
        System.out.println(result);
        Integer a = new Integer(122);
        Integer b = new Integer(122);
        Integer c = 127;
        Integer d = 127;
        //System.out.println(a == b);
        //System.out.println(c == d);
        String s = "a";
        String s2 = "a";
        String s3 = new String("ab").intern();
        System.out.println(s == s2);
        String s4 = "ab";
        System.out.println(s3 == s4);
    }
    public void getListOfWDBetweenDates(LocalDate beginDate, LocalDate endDate, String country){
        final String getListOfWDBD = host + "/getListOfWorkingDaysBetweenDates?" +
                "BeginDate=" + beginDate.toString() +
                "&EndDate=" + endDate.toString() +
                "&Country=" + country;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(getListOfWDBD, String.class);
        System.out.println(result);
    }
    public void addDaysToDate(LocalDate inDate, String country, boolean withHolidays, int count){
        final String addDToD = host + "/addDaysToDate?" +
                "InDate=" + inDate.toString() +
                "&Country=" + country +
                "&withHolidays=" + withHolidays +
                "&count=" + count;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForEntity(addDToD, String.class).toString();
        System.out.println(result);
    }

}
