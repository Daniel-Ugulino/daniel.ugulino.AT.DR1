package com.infnet.daniel.ugulino.AT.DR1.services;

import com.infnet.daniel.ugulino.AT.DR1.entity.Aluno;
import com.infnet.daniel.ugulino.AT.DR1.repository.alunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class alunoService {
    @Autowired
    private alunoRepository alunoRepository;

    public Aluno findById(Long id){
        return alunoRepository.findById(id).get();
    }

    public List<Aluno> getAll(){
        return alunoRepository.findAll();
    }

    public Aluno save(Aluno aluno){
        return alunoRepository.save(aluno);
    }
}
