package com.infnet.daniel.ugulino.AT.DR1.repository;

import com.infnet.daniel.ugulino.AT.DR1.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface disciplinaRepository extends JpaRepository<Disciplina, Long> {
    Optional<Disciplina> findByCodigo(Long codigo);
}
