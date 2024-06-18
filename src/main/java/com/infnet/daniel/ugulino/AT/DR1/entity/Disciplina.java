package com.infnet.daniel.ugulino.AT.DR1.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="disciplina")
@Getter
@Setter
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long codigo;
    private String nome;

    public Disciplina(Long codigo, String nome) {
        this.setCodigo(codigo);
        this.setNome(nome);
    }

    public Disciplina() {
    }
}
