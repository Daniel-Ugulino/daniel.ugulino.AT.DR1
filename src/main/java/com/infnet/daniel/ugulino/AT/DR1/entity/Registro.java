package com.infnet.daniel.ugulino.AT.DR1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="disciplina")
@Getter
@Setter
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Disciplina disciplina;

    private Double nota;

    public Registro(Disciplina disciplina, Aluno aluno, double v) {
        this.disciplina = disciplina;
        this.aluno = aluno;
        this.nota = v;
    }

    public Registro() {
    }
}
