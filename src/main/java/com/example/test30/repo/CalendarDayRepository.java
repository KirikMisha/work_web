package com.example.test30.repo;

import com.example.test30.models.CalendarDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository

public interface CalendarDayRepository extends JpaRepository<CalendarDay, Long> {
    CalendarDay findByDate(LocalDate date);
    CalendarDay findByDayAndMonth(int day, int month);
}
