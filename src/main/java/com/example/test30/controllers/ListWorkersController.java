package com.example.test30.controllers;

import com.example.test30.models.PersonsEntity;
import com.example.test30.repo.PersonsRepository;
import com.example.test30.services.CalendarDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/list")
public class ListWorkersController {
    private final PersonsRepository personRepository;
    private final ResourceLoader resourceLoader;
    @Autowired
    private CalendarDayService calendarDayService;

    @Autowired
    public ListWorkersController(PersonsRepository personRepository, ResourceLoader resourceLoader) {
        this.personRepository = personRepository;
        this.resourceLoader = resourceLoader;
    }

    @GetMapping
    public String getList(Model model, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        if (firstName == null) {
            firstName = "";
        }
        if (lastName == null) {
            lastName = "";
        }
        List<PersonsEntity> persons = personRepository.findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(lastName, firstName);
        model.addAttribute("persons", persons);
        return "list-main";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<PersonsEntity> getPersonsByFirstNameAndLastName(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        if (firstName == null) {
            firstName = "";
        }
        if (lastName == null) {
            lastName = "";
        }
        List<PersonsEntity> persons = personRepository.findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(lastName, firstName);
        return persons;
    }

    @GetMapping("/parsing")
    public String parseAndSave(Model model) {
//        String filePath = "classpath:static/fil.xlsx";
        try {
            // Выполните здесь парсинг для календарных дней
            calendarDayService.scrapeCalendarDays();
            model.addAttribute("message", "Парсинг выполнен успешно!");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ошибка чтения файла: " + e.getMessage());
        }

        return "list-main";
    }

    @GetMapping("/add")
    public String listAdd(Model model) {
        return "user-add";
    }

    @PostMapping("/add")
    public String listPostAdd(@ModelAttribute PersonsEntity person) {
        personRepository.save(person);
        return "redirect:/list";
    }
}
