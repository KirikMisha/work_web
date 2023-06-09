package com.example.test30.repo;

import com.example.test30.models.PersonsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonsRepository extends JpaRepository<PersonsEntity, Long> {
    List<PersonsEntity> findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(String lastName, String firstName);
}
