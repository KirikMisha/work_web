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

    private final String CALENDAR_URL = "https://www.calend.ru/holidays/may/";

    @Autowired
    private CalendarDayRepository calendarDayRepository;

    public void scrapeCalendarDays() {
        try {
            // Get the document from the calendar URL
            Document document = Jsoup.connect(CALENDAR_URL).get();

            // Find all the calendar day elements
            Elements dayElements = document.select("li.full");
            for (Element dayElement : dayElements) {
                // Get the date and format it
                String dataNum = dayElement.select(".dataNum a").attr("href").split("/")[2];
                String dateDesc = dayElement.select(".dataNum .desc").text();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                LocalDate date = LocalDate.parse(dataNum, formatter);

                // Get the description
                String description = dayElement.select(".caption").first().text();

                // Get the additional information columns
                Elements captionElements = dayElement.select(".caption");
                List<String> additionalInfoList = new ArrayList<>();
                for (Element captionElement : captionElements) {
                    additionalInfoList.add(captionElement.text());
                }

                // Get the link to international information
                Element linkElement = dayElement.selectFirst(".caption .title a[href]");
                String link = linkElement.attr("href");

                // Scrape international information from the link
                List<String> internationalInformationList = scrapeInternationalInformation(link);

                // Create a new CalendarDay object and set the values
                CalendarDay calendarDay = new CalendarDay();
                calendarDay.setDate(date);
                calendarDay.setDescription(description);

                // Set additional information columns
                for (int i = 0; i < additionalInfoList.size() && i < 10; i++) {
                    String additionalInfoColumn = "additionalInfo" + (i + 1);
                    setAdditionalInfoColumn(calendarDay, additionalInfoColumn, additionalInfoList.get(i));
                }

                // Set international information columns
                for (int i = 0; i < internationalInformationList.size() && i < 9; i++) {
                    String internationalInfoColumn = "internationalInformation" + (i + 1);
                    setInternationalInfoColumn(calendarDay, internationalInfoColumn, internationalInformationList.get(i));
                }

                // Save the CalendarDay object to the database
                calendarDayRepository.save(calendarDay);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAdditionalInfoColumn(CalendarDay calendarDay, String column, String value) {
        try {
            calendarDay.getClass().getMethod("set" + capitalize(column), String.class).invoke(calendarDay, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setInternationalInfoColumn(CalendarDay calendarDay, String column, String value) {
        try {
            calendarDay.getClass().getMethod("set" + capitalize(column), String.class).invoke(calendarDay, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    private List<String> scrapeInternationalInformation(String link) {
        List<String> internationalInformationList = new ArrayList<>();
        try {
            Document document = Jsoup.connect(link).get();
            Elements internationalInfoElements = document.select(".caption .title a[href]");
            for (Element internationalInfoElement : internationalInfoElements) {
                String internationalLink = internationalInfoElement.attr("href");
                Document internationalDocument = Jsoup.connect(internationalLink).get();
                Elements internationalTextElements = internationalDocument.select("div.maintext > p");
                if (!internationalTextElements.isEmpty()) {
                    StringBuilder internationalInfoBuilder = new StringBuilder();
                    for (Element element : internationalTextElements) {
                        internationalInfoBuilder.append(element.text()).append("\n");
                    }
                    internationalInformationList.add(internationalInfoBuilder.toString().trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return internationalInformationList;
    }

    public CalendarDay getCalendarDayByDate(LocalDate date) {
        return calendarDayRepository.findByDate(date);
    }

    public List<CalendarDay> getAllCalendarDays() {
        return calendarDayRepository.findAll();
    }

    public CalendarDay getCalendarDayById(Long id) {
        return calendarDayRepository.findById(id).orElse(null);
    }
}
