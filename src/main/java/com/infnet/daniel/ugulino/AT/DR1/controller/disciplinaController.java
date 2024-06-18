package com.infnet.daniel.ugulino.AT.DR1.controller;

import com.infnet.daniel.ugulino.AT.DR1.DTO.DisciplinaDTO;
import com.infnet.daniel.ugulino.AT.DR1.entity.Disciplina;
import com.infnet.daniel.ugulino.AT.DR1.services.disciplinaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplina")
public class disciplinaController {
    @Autowired
    private disciplinaService disciplinaService;

    @GetMapping("/getByCode/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        Disciplina disciplina;
        try {
           disciplina = disciplinaService.findByCodigo(id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao Encontrar Disciplina");
        }

        return ResponseEntity.status(HttpStatus.OK).body(disciplina);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody @Valid DisciplinaDTO disciplinaDTO){
        try {
            Disciplina disciplinaModel = new Disciplina();
            BeanUtils.copyProperties(disciplinaDTO,disciplinaModel);
            disciplinaService.save(disciplinaModel);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao Salvar Disciplina");
        }
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> list(){
        List<Disciplina> disciplinas;
        try {
            disciplinas = disciplinaService.getAll();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Erro ao Listar Disciplina");
        }

        return ResponseEntity.status(HttpStatus.OK).body(disciplinas);
    }
}
