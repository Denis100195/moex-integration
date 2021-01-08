package com.stock.analysis.moex.integration.client;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class BusinessCalendarClient {

    @Value("${host}")
    private String host;

    public void getNextWorkingDate(String date,String country){
        final String getNextWD = "http://localhost:8080/getNextWorkingDate?InDate=" + date + "&Country=" + country;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(getNextWD, String.class);
        System.out.println(result);
    }
    public LocalDate getPreviousWorkingDate(LocalDate date, String country){
        final String getPrevWD = host + "/getPreviousWorkingDate?InDate=" + date.toString() + "&Country=" + country;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LocalDate> entity = restTemplate.getForEntity(getPrevWD, LocalDate.class);
        return entity.getBody();
    }

    public void isDateWorkingDay(String date, String country){
        final String isDateWD = host + "/isDateWorkingDay?InDate=" + date + "&Country=" + country;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(isDateWD, String.class);
        System.out.println(result);
    }
    public void getWorkingDaysBetweenDates(String beginDate, String endDate, String country){
        final String getWDBD = host + "/getWorkingDaysBetweenDates?BeginDate=" + beginDate +
                "EndDate=" + endDate +
                "&Country=" + country;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(getWDBD, String.class);
        System.out.println(result);
    }
    public void getListOfWDBetweenDates(String beginDate, String endDate, String country){
        final String getListOfWDBD = host + "/getListOfWorkingDaysBetweenDates?BeginDate=" + beginDate +
                "EndDate=" + endDate +
                "&Country=" + country;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(getListOfWDBD, String.class);
        System.out.println(result);
    }
    public void addDaysToDate(String inDate, String country, boolean withHolidays, int count){
        final String addDToD = host + "/getListOfWorkingDaysBetweenDates?InDate=" + inDate +
                "&Country=" + country +
                "withHolidays=" + withHolidays +
                "count" + count;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForEntity(addDToD, String.class).toString();
        System.out.println(result);
    }

}
