package org.business.commons.calendar.restControllers;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;



@RestController
//@RequestMapping("/")
public class CalendarController {

    private static final Logger log = LoggerFactory.getLogger(CalendarController.class);

    @Value("#{${holidays.map}}")
    private Map<String,String> holMap;

    @Autowired
    FactCalendar factCalendar;

    /**
     * Метод возвращает следующий рабочий день в виде даты
     * @param inDate Дата в формате dd.MM.yyyyy
     * @param country Наименование страны
     * @return Дата следующего рабочего дня
     * @throws ParseException
     */
    //@RequestMapping("/getNextWorkingDate")
    @GetMapping(value = "/getNextWorkingDate")
    public String getNextWorkingDate(@RequestParam(value="InDate") String inDate,
                                     @RequestParam(value="Country") String country
                                   ) throws ParseException {


        String out = DateFormatUtils.format(factCalendar.getNextWorkingDate(DateUtils.parseDate(inDate, "dd.MM.yyyy"), country), "dd.MM.yyyy");
        log.info("Next working day in " + country + " is " +   out);
        return   out;
    }


    /**
     * Метод возвращает предыдущий рабочий день в виде даты
     * @param inDate Дата в формате dd.MM.yyyyy
     * @param country Наименование страны
     * @return Дата предыдущего рабочего дня dd.MM.yyyy
     * @throws ParseException
     */
    //@RequestMapping("/getPreviousWorkingDate")
    @GetMapping(value = "/getPreviousWorkingDate")
    public String getPreviousWorkingDate(@RequestParam(value="InDate") String inDate,
                                     @RequestParam(value="Country") String country
    ) throws ParseException {


        String out = DateFormatUtils.format(factCalendar.getPreviousWorkingDate(DateUtils.parseDate(inDate, "dd.MM.yyyy"), country), "dd.MM.yyyy");
        log.info("Previous working day in " + country + " is " +   out);
        return   out;
    }


    /**
     * Метод возвращает true если переданная дата в указанной стране являетс рабочим днём и false, если дата является выходным или праздничным днем.
     * @param inDate Дата в формате dd.MM.yyyyy
     * @param country Наименование страны
     * @return true/false
     * @throws ParseException
     */
    //@RequestMapping("/isDateWorkingDay")
    @GetMapping(value = "/isDateWorkingDay")
    public boolean isDateWorkingDay(@RequestParam(value="InDate") String inDate,
                                    @RequestParam(value="Country") String country
    ) throws ParseException {

       boolean isHoliday = factCalendar.isHoliday(DateUtils.parseDate(inDate, "dd.MM.yyyy"), country);
       log.info(inDate + " is holiday in " + country + ": " + isHoliday);

        if (isHoliday == true) {
            return false;
        } else {
            return true;
        }

    }


    /**
     * Method returns the number of working days between two dates (begin and ens dates are inclusive)
     * @param beginDate, dd.MM.yyyyy
     * @param endDate, dd.MM.yyyyy
     * @param country e.g. Russia
     * @return int, number of days
     * @throws ParseException
     */






    //@RequestMapping(method = RequestMethod.GET, path="/getWorkingDaysBetweenDates", produces = "text/plain")
    @GetMapping(value = "/getWorkingDaysBetweenDates", produces = "text/plain")
    public int getWorkingDaysBetweenDates(@RequestParam(value="BeginDate") String beginDate,
                                              @RequestParam(value="EndDate") String endDate,
                                              @RequestParam(value="Country") String country
    ) throws ParseException {

        int workingDaysBetweenDates = factCalendar.getWorkingDaysBetweenDates(beginDate, endDate, country);
        log.info("Between " + beginDate + " and " +endDate+  " the number of working day is  " + workingDaysBetweenDates);

        return workingDaysBetweenDates;

    }



/*
    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }*/




}
