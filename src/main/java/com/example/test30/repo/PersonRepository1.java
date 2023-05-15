package com.example.test30.repo;

import com.example.test30.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository1 {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Person person) {
        String sql = "INSERT INTO person (first_name, last_name, middle_name, position, office_number, phone_number) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, person.getFirstName(), person.getLastName(), person.getMiddleName(), person.getPosition(),
                person.getOfficeNumber(), person.getPhoneNumber());
    }
}
