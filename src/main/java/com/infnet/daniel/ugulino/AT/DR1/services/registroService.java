package com.infnet.daniel.ugulino.AT.DR1.services;

import com.infnet.daniel.ugulino.AT.DR1.entity.Aluno;
import com.infnet.daniel.ugulino.AT.DR1.entity.Registro;
import com.infnet.daniel.ugulino.AT.DR1.repository.alunoRepository;
import com.infnet.daniel.ugulino.AT.DR1.repository.disciplinaRepository;
import com.infnet.daniel.ugulino.AT.DR1.repository.registroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class registroService {
    @Autowired
    private registroRepository registroRepository;

    public Registro save(Registro registro) {
        return registroRepository.save(registro);
    }

    public Registro findById(Long id) {
       return registroRepository.findById(id).orElse(null);
    }

    public List<Registro> getAll() {
        return registroRepository.findAll();
    }

    public List<Registro> getAprovados(Long disciplinaId) {
        return registroRepository.findByDisciplinaCodigoAndNotaGreaterThanEqual(disciplinaId,7.0);
    }

    public List<Registro> getReprovados(Long disciplinaId) {
        return registroRepository.findByDisciplinaCodigoAndNotaLessThan(disciplinaId,7.0);

    }
}
