package com.example.test30.repo;

import com.example.test30.models.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository1 extends JpaRepository<PersonEntity, Long> {
    List<PersonEntity> findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(String lastName, String firstName);
}
