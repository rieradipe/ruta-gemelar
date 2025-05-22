package com.rutagemelar.back.repository;

import com.rutagemelar.back.model.SemanaEmbarazo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SemanaEmbarazoRepository extends JpaRepository<SemanaEmbarazo, Long> {
    Optional<SemanaEmbarazo> findByNumeroSemanaAndTipoEmbarazo(int numeroSemana, String tipoEmbarazo);
}



