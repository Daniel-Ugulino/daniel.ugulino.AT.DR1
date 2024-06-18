package com.infnet.daniel.ugulino.AT.DR1.controller;

import com.infnet.daniel.ugulino.AT.DR1.DTO.AlunoDTO;
import com.infnet.daniel.ugulino.AT.DR1.entity.Aluno;
import com.infnet.daniel.ugulino.AT.DR1.services.alunoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class alunoController {
    @Autowired
    private alunoService alunoService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        Aluno aluno;

        try {
            aluno = alunoService.findById(id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao retornar Aluno");
        }

        return ResponseEntity.status(HttpStatus.OK).body(aluno);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody @Valid AlunoDTO alunoDTO){
        try {
            Aluno alunoModel = new Aluno();
            BeanUtils.copyProperties(alunoDTO,alunoModel);
            alunoService.save(alunoModel);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao Salvar Aluno");
        }

        return ResponseEntity.status(HttpStatus.OK).body(alunoDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> list(){
        List<Aluno> alunos;
        try {
            alunos = alunoService.getAll();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Erro ao listar Aluno");
        }

        return ResponseEntity.status(HttpStatus.OK).body(alunos);
    }
}
