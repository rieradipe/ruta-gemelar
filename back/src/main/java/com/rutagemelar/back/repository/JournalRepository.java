package com.rutagemelar.back.repository;

import com.rutagemelar.back.model.JournalEntry;
import com.rutagemelar.back.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JournalRepository extends JpaRepository<JournalEntry, Long> {
    List<JournalEntry> findByUsuariaOrderByFechaDesc(User usuaria);
}
