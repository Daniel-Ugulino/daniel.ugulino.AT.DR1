package com.infnet.daniel.ugulino.AT.DR1.services;

import com.infnet.daniel.ugulino.AT.DR1.entity.Disciplina;
import com.infnet.daniel.ugulino.AT.DR1.repository.disciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class disciplinaService {
    @Autowired
    private disciplinaRepository disciplinaRepository;

    public Disciplina save(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }
    public Disciplina findByCodigo(Long codigo) {
        return disciplinaRepository.findByCodigo(codigo).orElse(null);
    }
    public Disciplina findById(Long id) {
        return disciplinaRepository.findById(id).orElse(null);
    }

    public List<Disciplina> getAll() {
        return disciplinaRepository.findAll();
    }
}
