package com.rutagemelar.back.repository;

import com.rutagemelar.back.model.SemanaEmbarazo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemanaEmbarazoRepository extends JpaRepository<SemanaEmbarazo, Long> {
    List<SemanaEmbarazo> findByNumeroSemana (int numeroSemana);
    List<SemanaEmbarazo> findByNumeroSemanaAndTipoEmbarazo(int numeroSemana, String tipoEmbarazo);
}



