package com.example.test30.services;

import com.example.test30.models.PersonEntity;
import com.example.test30.repo.PersonRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ParserService {
    private final CalendarDayService calendarDayService;
    private final ExcelReader excelReader;
    private final PersonRepository1 personRepository;

    @Autowired
    public ParserService(CalendarDayService calendarDayService, ExcelReader excelReader, PersonRepository1 personRepository) {
        this.calendarDayService = calendarDayService;
        this.excelReader = excelReader;
        this.personRepository = personRepository;
    }

    public void parseAndSave(String filePath) throws IOException {
        List<PersonEntity> persons = excelReader.readExcelFile(filePath);

        for (PersonEntity person : persons) {
            if (personRepository.findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(person.getLastName(), person.getFirstName()) == null) {
                personRepository.save(person);
            }
        }
    }
}
