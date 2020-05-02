package org.businessday.api.calendar.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.businessday.api.calendar.exception.YearNotSupportedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

@Service
@Slf4j
public class JsonCalendar {


    private final Gson gson = new Gson();
    @Value("classpath:calendar.json")
    private Resource resourceFile;
    private JsonObject calendarObject;

    @PostConstruct
    private void postConstruct() {
        calendarObject = convertResourceFileToJSON();
    }

    public void checkIsYearSupported(Integer year, String country) throws YearNotSupportedException {
        JsonObject jsonObject = calendarObject.get("holidays").getAsJsonObject().get(country).getAsJsonObject();
        if (!jsonObject.has(year.toString())) {
            Set<String> allowedYears = jsonObject.keySet();
            throw new YearNotSupportedException(year + " год не поддерживается. Допустимые значения для " + country + ": " + String.join(",", allowedYears));
        }
    }

    public String getCountryHolidays(String country) {
        JsonObject jsonObject = calendarObject.get("holidays").getAsJsonObject().get(country).getAsJsonObject();
        return gson.toJson(jsonObject);
    }

    public String getWorkingHolidays(String country) {
        JsonObject jsonObject = calendarObject.get("working.holidays").getAsJsonObject().get(country).getAsJsonObject();
        return gson.toJson(jsonObject);
    }

    private JsonObject convertResourceFileToJSON() {
        JsonObject jsonObject = new JsonObject();
        try {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(new FileReader(resourceFile.getFile()));
            jsonObject = jsonElement.getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
