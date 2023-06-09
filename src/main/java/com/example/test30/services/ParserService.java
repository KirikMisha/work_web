package com.example.test30.services;

import com.example.test30.models.PersonsEntity;
import com.example.test30.repo.PersonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ParserService {
    private final CalendarDayService calendarDayService;
//    private final ExcelReader excelReader;
    private final PersonsRepository personRepository;

    @Autowired
//    ExcelReader excelReader,
    public ParserService(CalendarDayService calendarDayService, PersonsRepository personRepository) {
        this.calendarDayService = calendarDayService;
//        this.excelReader = excelReader;
        this.personRepository = personRepository;
    }

//    public void parseAndSave(String filePath) throws IOException {
//        List<PersonsEntity> persons = excelReader.readExcelFile(filePath);
//
//        for (PersonsEntity person : persons) {
//            if (personRepository.findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(person.getLastName(), person.getFirstName()) == null) {
//                personRepository.save(person);
//            }
//        }
//    }
}
