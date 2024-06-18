package com.infnet.daniel.ugulino.AT.DR1.repository;

import com.infnet.daniel.ugulino.AT.DR1.entity.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface registroRepository extends JpaRepository<Registro, Long> {
    List<Registro> findByDisciplinaCodigoAndNotaGreaterThanEqual(Long disciplinaCodigo, Double nota);
    List<Registro> findByDisciplinaCodigoAndNotaLessThan(Long disciplinaCodigo, Double nota);
}
