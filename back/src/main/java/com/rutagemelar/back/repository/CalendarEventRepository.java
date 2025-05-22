package com.rutagemelar.back.repository;

import com.rutagemelar.back.model.CalendarEvent;
import com.rutagemelar.back.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Long> {
    List<CalendarEvent> findByUsuariaOrderByFechaAsc(User usuaria);
}

