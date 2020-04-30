package org.business.commons.calendar.restControllers;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.concurrent.TimeUnit.DAYS;

/**
 * Created by User on 9/2/2017.
 */
@Service
public class FactCalendar {

    private static final Logger log = LoggerFactory.getLogger(FactCalendar.class);


    @Value("#{${holidays.map}}")
    private Map<String, String> holMap;

    /**
     * Is date a holiday in the specified country
     *
     * @param d date
     * @param country country
     * @return true/false
     */
    public boolean isHoliday(Date d, String country) {


        String countryHoilidays = holMap.get(country);

        Calendar c = new GregorianCalendar();
        c.setTime(d);
        if ((Calendar.SATURDAY == c.get(c.DAY_OF_WEEK)) || (Calendar.SUNDAY == c.get(c.DAY_OF_WEEK)) || StringUtils.contains(countryHoilidays, DateFormatUtils.format(d, "dd.MM.yy"))) {
            log.info(DateFormatUtils.format(d, "dd.MM.yyyy") + " is a holiday, a weekend or a pre-holiday day in " + country);
            return true;
        } else {
            log.info(DateFormatUtils.format(d, "dd.MM.yyyy") + " is NOT a holiday, a weekend or a pre-holiday day in " + country);
            return false;
        }
    }


    /**
     * Метод возвращает следующий рабочий день
     *
     * @param d       дата
     * @param country страна
     * @return
     */

    public Date getNextWorkingDate(Date d, String country) {

        d = DateUtils.addDays(d, 1);

        while (isHoliday(d, country)) {
            d = DateUtils.addDays(d, 1);
        }
        return d;
    }


    /**
     * Метод возвращает предыдущий рабочий день
     *
     * @param d       дата
     * @param country страна
     * @return
     */

    public Date getPreviousWorkingDate(Date d, String country) {

        d = DateUtils.addDays(d, -1);

        while (isHoliday(d, country)) {
            d = DateUtils.addDays(d, -1);
        }
        return d;
    }


    /**
     * Returns the number of working days between two dates (inclusive begin and end dates)
     *
     * @param beginDate begin Date, dd.MM.yyyyy
     * @param endDate   end Date, dd.MM.yyyyy
     * @return
     */
    public int getWorkingDaysBetweenDates(String beginDate, String endDate, String country) throws ParseException {

        List<Date> workingDates = new ArrayList<>();

        List<Date> datesBetweenInclusive = getDatesBetweenInclusive(DateUtils.parseDate(beginDate, "dd.MM.yyyy"), DateUtils.parseDate(endDate, "dd.MM.yyyy"));

        for (Date dt:datesBetweenInclusive) {
            boolean holiday = isHoliday(dt, country);
            if (holiday == false) {
                log.info("Add " + DateFormatUtils.format(dt, "dd.MM.yyyy") + " as working date to out array.");
                workingDates.add(dt);
            }

        }

          return workingDates.size();
    }


    /**Method returns date array between two dates inclusive begin date and end date
     * @param beginDate
     * @param endDate
     * @return The massive of dates
     */
    public List<Date> getDatesBetweenInclusive(
            Date beginDate, Date endDate) {

        List<Date> out = new ArrayList<>();

        List<LocalDate> localDates = new ArrayList<LocalDate>();
        for (LocalDate date = asLocalDate(beginDate); !date.isAfter(asLocalDate(endDate)); date = date.plusDays(1)) {
            localDates.add(date);
        }


        for (int i = 0; i <localDates.size() ; i++) {

            out.add(asDate(localDates.get(i)));
        }

        return out;

    }


    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }



    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }



    }



