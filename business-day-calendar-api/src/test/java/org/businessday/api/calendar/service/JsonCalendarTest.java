package org.businessday.api.calendar.service;

import org.businessday.api.calendar.exception.YearNotSupportedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class JsonCalendarTest {

    @Autowired
    private JsonCalendar jsonReader;


    @Test
    public void testReadCalendar() {
        String holidays = jsonReader.getCountryHolidays("RUS");
    }

    @Test
    public void testIsYearSupported() throws YearNotSupportedException {
        jsonReader.checkIsYearSupported(2019, "RUS");
    }

    @Test(expected = YearNotSupportedException.class)
    public void testIsYearNotSupported() throws YearNotSupportedException {
        jsonReader.checkIsYearSupported(2021, "RUS");
    }
}