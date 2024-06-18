package com.infnet.daniel.ugulino.AT.DR1.controller;

import com.infnet.daniel.ugulino.AT.DR1.DTO.RegistroDTO;
import com.infnet.daniel.ugulino.AT.DR1.entity.Aluno;
import com.infnet.daniel.ugulino.AT.DR1.entity.Disciplina;
import com.infnet.daniel.ugulino.AT.DR1.entity.Registro;
import com.infnet.daniel.ugulino.AT.DR1.services.registroService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registro")
public class registroController {
    @Autowired
    private registroService registroService;
    @Autowired
    private com.infnet.daniel.ugulino.AT.DR1.services.disciplinaService disciplinaService;
    @Autowired
    private com.infnet.daniel.ugulino.AT.DR1.services.alunoService alunoService;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody @Valid RegistroDTO registroDTO){
        Registro newRegistro = new Registro();
        Aluno aluno;
        Disciplina disciplina;
        try {
            disciplina = disciplinaService.findByCodigo(registroDTO.getDisciplina());
            aluno = alunoService.findById(registroDTO.getAluno());
            newRegistro.setDisciplina(disciplina);
            newRegistro.setAluno(aluno);
            newRegistro.setNota(registroDTO.getNota());
            registroService.save(newRegistro);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao Salvar Disciplina");
        }
        return ResponseEntity.status(HttpStatus.OK).body(newRegistro);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> list(){
        List<Registro> registros;
        try {
            registros = registroService.getAll();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Erro ao Encontrar Registros");
        }

        return ResponseEntity.status(HttpStatus.OK).body(registros);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        Registro registro;
        try {
            registro = registroService.findById(id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Erro ao Encontrar Registros");
        }

        return ResponseEntity.status(HttpStatus.OK).body(registro);
    }

    @GetMapping("/get/aprovados/{id}")
    public ResponseEntity<Object> listAprovado(@PathVariable Long id){
        List<Registro> registros;
        try {
            registros = registroService.getAprovados(id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Erro ao Listar Registros Aprovados");
        }

        return ResponseEntity.status(HttpStatus.OK).body(registros);
    }

    @GetMapping("/get/reprovados/{id}")
    public ResponseEntity<Object> listReprovado(@PathVariable Long id){
        List<Registro> registros;
        try {
            registros = registroService.getReprovados(id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Erro ao Listar Registros Reprovados");
        }

        return ResponseEntity.status(HttpStatus.OK).body(registros);
    }
}
