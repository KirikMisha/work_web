package com.example.test30.controllers;


import com.example.test30.models.CalendarDay;
import com.example.test30.services.CalendarDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/details")
public class InfoCalendarController {
    @Autowired
    private CalendarDayService calendarDayService;

    @GetMapping("/info1")
    public String showInfo1(@RequestParam("id") Long id, Model model) {
        CalendarDay calendarDay = calendarDayService.getCalendarDayById(id);
        if (calendarDay != null) {
            String internationalInformation1 = calendarDay.getInternationalInformation1();
            model.addAttribute("internationalInformation1", internationalInformation1);
            model.addAttribute("id", id); // Добавлен атрибут id
        }
        return "calendar-day-info";
    }

    @GetMapping("/info2")
    public String showInfo2(@RequestParam("id") Long id, Model model) {
        CalendarDay calendarDay = calendarDayService.getCalendarDayById(id);
        if (calendarDay != null) {
            String internationalInformation2 = calendarDay.getInternationalInformation2();
            model.addAttribute("internationalInformation2", internationalInformation2);
            model.addAttribute("id", id); // Добавлен атрибут id
        }
        return "calendar-day-info";
    }

    @GetMapping("/info3")
    public String showInfo3(@RequestParam("id") Long id, Model model) {
        CalendarDay calendarDay = calendarDayService.getCalendarDayById(id);
        if (calendarDay != null) {
            String internationalInformation3 = calendarDay.getInternationalInformation3();
            model.addAttribute("internationalInformation3", internationalInformation3);
            model.addAttribute("id", id); // Добавлен атрибут id
        }
        return "calendar-day-info";
    }
    @GetMapping("/info4")
    public String showInfo4(@RequestParam("id") Long id, Model model) {
        CalendarDay calendarDay = calendarDayService.getCalendarDayById(id);
        if (calendarDay != null) {
            String internationalInformation4 = calendarDay.getInternationalInformation4();
            model.addAttribute("internationalInformation4", internationalInformation4);
            model.addAttribute("id", id); // Добавлен атрибут id
        }
        return "calendar-day-info";
    }
    @GetMapping("/info5")
    public String showInfo5(@RequestParam("id") Long id, Model model) {
        CalendarDay calendarDay = calendarDayService.getCalendarDayById(id);
        if (calendarDay != null) {
            String internationalInformation5 = calendarDay.getInternationalInformation5();
            model.addAttribute("internationalInformation5", internationalInformation5);
            model.addAttribute("id", id); // Добавлен атрибут id
        }
        return "calendar-day-info";
    }
    @GetMapping("/info6")
    public String showInfo6(@RequestParam("id") Long id, Model model) {
        CalendarDay calendarDay = calendarDayService.getCalendarDayById(id);
        if (calendarDay != null) {
            String internationalInformation6 = calendarDay.getInternationalInformation6();
            model.addAttribute("internationalInformation6", internationalInformation6);
            model.addAttribute("id", id); // Добавлен атрибут id
        }
        return "calendar-day-info";
    }
    @GetMapping("/info7")
    public String showInfo7(@RequestParam("id") Long id, Model model) {
        CalendarDay calendarDay = calendarDayService.getCalendarDayById(id);
        if (calendarDay != null) {
            String internationalInformation7 = calendarDay.getInternationalInformation7();
            model.addAttribute("internationalInformation7", internationalInformation7);
            model.addAttribute("id", id); // Добавлен атрибут id
        }
        return "calendar-day-info";
    }


}
