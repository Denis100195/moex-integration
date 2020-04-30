package org.businessday.api.calendar.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.businessday.api.calendar.exception.YearNotSupportedException;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BusinessDayCalendarService {

    private final JsonCalendar calendar;

    public BusinessDayCalendarService(JsonCalendar calendar) {
        this.calendar = calendar;
    }

    /**
     * Is date a holiday in the specified country
     *
     * @param date    date
     * @param country country
     * @return true/false
     */
    public boolean isHoliday(LocalDate date, String country) {
        String countryHolidays = calendar.getCountryHolidays(country);
        String countryWorkingHolidays = calendar.getWorkingHolidays(country);

        String dateAsString = date.toString();

        // Если рабочий день приходится на выходной
        if (countryWorkingHolidays.contains(dateAsString)) {
            return false;
        }

        if ((DayOfWeek.SATURDAY == date.getDayOfWeek()) || (DayOfWeek.SUNDAY == date.getDayOfWeek()) || StringUtils.contains(countryHolidays, dateAsString)) {
            log.info(dateAsString + " is a holiday, a weekend or a pre-holiday day in " + country);
            return true;
        } else {
            log.info(dateAsString + " is NOT a holiday, a weekend or a pre-holiday day in " + country);
            return false;
        }
    }

    /**
     * Метод возвращает следующий рабочий день
     *
     * @param date    дата
     * @param country страна
     * @return
     */
    public LocalDate getNextWorkingDate(LocalDate date, String country) throws YearNotSupportedException {
        calendar.checkIsYearSupported(date.getYear(), country);
        date = date.plusDays(1);

        while (isHoliday(date, country)) {
            date = date.plusDays(1);
        }
        return date;
    }

    /**
     * Метод возвращает предыдущий рабочий день
     *
     * @param date    дата
     * @param country страна
     * @return
     */
    public LocalDate getPreviousWorkingDate(LocalDate date, String country) throws YearNotSupportedException {
        calendar.checkIsYearSupported(date.getYear(), country);
        date = date.minusDays(1);

        while (isHoliday(date, country)) {
            date = date.minusDays(1);
        }
        return date;
    }

    /**
     * Returns the number of working days between two dates (inclusive begin and end dates)
     *
     * @param beginDate begin LocalDate, yyyy-MM-dd
     * @param endDate   end LocalDate, yyyy-MM-dd
     * @return
     */
    public long getCountOfWorkingDaysBetweenDates(LocalDate beginDate, LocalDate endDate, String country) throws YearNotSupportedException {
        calendar.checkIsYearSupported(beginDate.getYear(), country);
        List<LocalDate> datesBetweenInclusive = getDatesBetweenInclusive(beginDate, endDate);
        return datesBetweenInclusive.stream()
                .filter(dt -> !isHoliday(dt, country))
                .count();
    }

    public List<LocalDate> getWorkingDaysBetweenDates(LocalDate beginDate, LocalDate endDate, String country) throws YearNotSupportedException {
        calendar.checkIsYearSupported(beginDate.getYear(), country);
        List<LocalDate> datesBetweenInclusive = getDatesBetweenInclusive(beginDate, endDate);
        return datesBetweenInclusive.stream()
                .filter(dt -> !isHoliday(dt, country))
                .collect(Collectors.toList());
    }


    public LocalDate addDaysToDate(LocalDate date, String country, boolean withHolidays, int count) throws YearNotSupportedException {
        calendar.checkIsYearSupported(date.getYear(), country);
        if (withHolidays) {
            return date.plusDays(count);
        } else {
            if (isHoliday(date, country)) {
                date = getNextWorkingDate(date, country);
                count--;
            }
            while (count != 0) {
                if (!isHoliday(date, country)) {
                    count--;
                    date = getNextWorkingDate(date, country);
                } else {
                    date = getNextWorkingDate(date, country);
                }
            }
            return date;
        }
    }

    /**
     * Method returns date array between two dates inclusive begin date and end date
     *
     * @param beginDate
     * @param endDate
     * @return The massive of dates
     */
    public List<LocalDate> getDatesBetweenInclusive(LocalDate beginDate, LocalDate endDate) {
        List<LocalDate> localDates = new ArrayList<>();
        for (LocalDate date = beginDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            localDates.add(date);
        }
        return localDates;
    }


}



