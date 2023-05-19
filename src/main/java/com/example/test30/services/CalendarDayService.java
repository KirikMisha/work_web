package com.example.test30.services;

import com.example.test30.models.CalendarDay;
import com.example.test30.repo.CalendarDayRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class CalendarDayService {

    private final String CALENDAR_URL = "https://www.calend.ru/holidays/august/";

    @Autowired
    private CalendarDayRepository calendarDayRepository;

    public void scrapeCalendarDays() {
        List<CalendarDay> calendarDays = new ArrayList<>();
        try {
            // Get the document from the calendar URL
            Document document = Jsoup.connect(CALENDAR_URL).get();

            // Find all of the calendar day elements
            Elements dayElements = document.select("li.full");
            for (Element dayElement : dayElements) {
                // Get the date and format it
                String dataNum = dayElement.select(".dataNum a").attr("href").split("/")[2];
                String dateDesc = dayElement.select(".dataNum .desc").text();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                LocalDate date = LocalDate.parse(dataNum, formatter);

                // Get the description
                String description = dayElement.select(".caption").first().text();

                // Create a new CalendarDay object and add it to the list
                CalendarDay calendarDay = new CalendarDay();
                calendarDay.setDate(date);
                calendarDay.setDescription(description);
                calendarDays.add(calendarDay);
            }

            // Find and remove duplicate records
            List<CalendarDay> duplicates = new ArrayList<>();
            for (CalendarDay calendarDay : calendarDays) {
                CalendarDay existingDay = calendarDayRepository.findByDate(calendarDay.getDate());
                if (existingDay != null) {
                    duplicates.add(calendarDay);
                }
            }
            calendarDays.removeAll(duplicates);

            // Save the calendar days to the database
            calendarDayRepository.saveAll(calendarDays);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CalendarDay getCalendarDayByDate(LocalDate date) {
        return calendarDayRepository.findByDate(date);
    }

    public List<CalendarDay> getAllCalendarDays() {
        return calendarDayRepository.findAll();
    }
}

