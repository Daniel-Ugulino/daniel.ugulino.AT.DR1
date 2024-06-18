package com.infnet.daniel.ugulino.AT.DR1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="aluno")
@Getter
@Setter
@AllArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;

    public Aluno(String nome, String cpf, String email, String telefone, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Aluno() {

    }
}
