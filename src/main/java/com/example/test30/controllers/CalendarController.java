package com.example.test30.controllers;

//import ch.qos.logback.core.model.Model;
import org.springframework.ui.Model;
import com.example.test30.models.CalendarDay;
import com.example.test30.services.CalendarDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CalendarController {

    @Autowired
    private final CalendarDayService calendarDayService;

    public CalendarController(CalendarDayService calendarDayService) {
        this.calendarDayService = calendarDayService;
    }

    @GetMapping("/")
    public String showCalendar(Model model) {
        LocalDate today = LocalDate.now();
        CalendarDay todayDay = calendarDayService.getCalendarDayByDate(today);
        model.addAttribute("todayDay", todayDay);
        List<CalendarDay> calendarDays = calendarDayService.getAllCalendarDays();
        model.addAttribute("calendarDays", calendarDays);model.addAttribute("homePage", true);
        model.addAttribute("title", "Главная страница");

        return "home";
    }

//    @GetMapping("/day")
//    public String showCalendar(Model model) {
//        calendarDayService.scrapeCalendarDays();
//        List<CalendarDay> calendarDays = calendarDayService.getAllCalendarDays();
//        model.addAttribute("calendarDays", calendarDays);
//        return "calendarDays";
//    }
}