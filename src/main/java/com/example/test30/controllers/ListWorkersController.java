package com.example.test30.controllers;

import com.example.test30.models.PersonEntity;
import com.example.test30.repo.PersonRepository1;
import com.example.test30.services.CalendarDayService;
import com.example.test30.services.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/list")
public class ListWorkersController {
    private final PersonRepository1 personRepository;
    private final ParserService parserService;
    private final ResourceLoader resourceLoader;
    @Autowired
    private CalendarDayService calendarDayService;


    @Autowired
    public ListWorkersController(PersonRepository1 personRepository, ParserService parserService, ResourceLoader resourceLoader) {
        this.personRepository = personRepository;
        this.parserService = parserService;
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
        List<PersonEntity> persons = personRepository.findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(lastName, firstName);
        model.addAttribute("persons", persons);
        return "list-main";
    }


    @GetMapping("/search")
    @ResponseBody // Добавьте это объявление, чтобы вернуть данные как JSON
    public List<PersonEntity> getPersonsByFirstNameAndLastName(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        if (firstName == null) {
            firstName = "";
        }
        if (lastName == null) {
            lastName = "";
        }
        List<PersonEntity> persons = personRepository.findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(lastName, firstName);
        return persons;
    }


    @GetMapping("/parsing")
    public String parseAndSave(Model model) {
        String filePath = "classpath:static/fil.xlsx";
        try {
            parserService.parseAndSave(resourceLoader.getResource(filePath).getFile().getPath());
            // Если парсинг выполнен успешно, вы можете добавить соответствующее сообщение в модель
            model.addAttribute("message", "Парсинг выполнен успешно!");
            calendarDayService.scrapeCalendarDays();
        } catch (Exception e) {
            // Логирование исключения или вывод сообщения об ошибке
            e.printStackTrace(); // Логирование стека трейса
            model.addAttribute("error", "Ошибка чтения файла: " + e.getMessage());
        }

        return "list-main";
    }

    @GetMapping("/add")
    public String listAdd(Model model) {
        return "user-add";
    }

    @PostMapping("/add")
    public String listPostAdd(@ModelAttribute PersonEntity person) {
        personRepository.save(person);
        return "redirect:/list";
    }
}
