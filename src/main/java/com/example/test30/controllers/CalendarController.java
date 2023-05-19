package com.example.test30.controllers;

import com.example.test30.services.ParserService;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.Model;
import com.example.test30.models.CalendarDay;
import com.example.test30.services.CalendarDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.test30.services.ParserService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class CalendarController {
    private final CalendarDayService calendarDayService;
    private final ParserService parserService;
    private final ResourceLoader resourceLoader;

    @Autowired
    public CalendarController(CalendarDayService calendarDayService, ParserService parserService, ResourceLoader resourceLoader) {
        this.calendarDayService = calendarDayService;
        this.parserService = parserService;
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/")
    public String showCalendar(Model model) {
        LocalDate today = LocalDate.now();
        CalendarDay todayDay = calendarDayService.getCalendarDayByDate(today);
        model.addAttribute("todayDay", todayDay);
        List<CalendarDay> calendarDays = calendarDayService.getAllCalendarDays();
        model.addAttribute("calendarDays", calendarDays);
        model.addAttribute("homePage", true);
        model.addAttribute("title", "Главная страница");

        return "home";
    }
}
