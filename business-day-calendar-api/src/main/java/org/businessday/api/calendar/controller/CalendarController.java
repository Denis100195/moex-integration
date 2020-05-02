package org.businessday.api.calendar.controller;

import lombok.extern.slf4j.Slf4j;
import org.businessday.api.calendar.exception.YearNotSupportedException;
import org.businessday.api.calendar.service.BusinessDayCalendarService;
import org.businessday.api.calendar.service.JsonCalendar;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@Slf4j
public class CalendarController {

    private final BusinessDayCalendarService businessDayCalendarService;

    public CalendarController(BusinessDayCalendarService businessDayCalendarService, JsonCalendar calendar) {
        this.businessDayCalendarService = businessDayCalendarService;
    }

    /**
     * Метод возвращает следующий рабочий день в виде даты
     *
     * @param inDate  Дата в формате yyyy-MM-dd
     * @param country Наименование страны
     * @return Дата следующего рабочего дня
     */
    @GetMapping(value = "/getNextWorkingDate")
    public LocalDate getNextWorkingDate(@RequestParam(value = "InDate") String inDate, @RequestParam(value = "Country") String country) throws YearNotSupportedException {
        return businessDayCalendarService.getNextWorkingDate(LocalDate.parse(inDate), country);
    }

    /**
     * Метод возвращает предыдущий рабочий день в виде даты
     *
     * @param inDate  Дата в формате yyyy-MM-dd
     * @param country Наименование страны
     * @return Дата предыдущего рабочего дня yyyy-MM-dd
     */
    @GetMapping(value = "/getPreviousWorkingDate")
    public LocalDate getPreviousWorkingDate(@RequestParam(value = "InDate") String inDate, @RequestParam(value = "Country") String country) throws YearNotSupportedException {
        return businessDayCalendarService.getPreviousWorkingDate(LocalDate.parse(inDate), country);
    }

    /**
     * Метод возвращает true если переданная дата в указанной стране являетс рабочим днём и false, если дата является выходным или праздничным днем.
     *
     * @param inDate  Дата в формате yyyy-MM-dd
     * @param country Наименование страны
     * @return true/false
     */
    @GetMapping(value = "/isDateWorkingDay")
    public boolean isDateWorkingDay(@RequestParam(value = "InDate") String inDate, @RequestParam(value = "Country") String country) {

        boolean isHoliday = businessDayCalendarService.isHoliday(LocalDate.parse(inDate), country);
        log.info(inDate + " is holiday in " + country + ": " + isHoliday);
        return !isHoliday;
    }

    /**
     * Method returns the number of working days between two dates (begin and ens dates are inclusive)
     *
     * @param beginDate, yyyy-MM-dd
     * @param endDate,   yyyy-MM-dd
     * @param country    e.g. Russia
     * @return int, number of days
     */

    @GetMapping(value = "/getWorkingDaysBetweenDates", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getWorkingDaysBetweenDates(@RequestParam(value = "BeginDate") String beginDate,
                                           @RequestParam(value = "EndDate") String endDate,
                                           @RequestParam(value = "Country") String country) throws YearNotSupportedException {

        long workingDaysBetweenDates = businessDayCalendarService.getCountOfWorkingDaysBetweenDates(LocalDate.parse(beginDate), LocalDate.parse(endDate), country);
        log.info("Between " + beginDate + " and " + endDate + " the number of working day is  " + workingDaysBetweenDates);
        return workingDaysBetweenDates;

    }

    @GetMapping(value = "/getListOfWorkingDaysBetweenDates", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LocalDate> getListOfWorkingDaysBetweenDates(@RequestParam(value = "BeginDate") String beginDate,
                                                            @RequestParam(value = "EndDate") String endDate,
                                                            @RequestParam(value = "Country") String country) throws YearNotSupportedException {
        return businessDayCalendarService.getWorkingDaysBetweenDates(LocalDate.parse(beginDate), LocalDate.parse(endDate), country);
    }

    @GetMapping(value = "/addDaysToDate")

    public LocalDate addDaysToDate(@RequestParam(value = "InDate") String inDate,
                                   @RequestParam(value = "Country") String country,
                                   @RequestParam(value = "withHolidays") boolean withHolidays,
                                   @RequestParam(value = "count") int count) throws YearNotSupportedException {
        return businessDayCalendarService.addDaysToDate(LocalDate.parse(inDate), country, withHolidays, count);
    }


/*
    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }*/


}
