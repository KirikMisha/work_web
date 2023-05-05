package com.example.test30.repo;

import com.example.test30.models.CalendarDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CalendarDayRepository extends JpaRepository<CalendarDay, Long> {
    CalendarDay findByDate(LocalDate date);
}