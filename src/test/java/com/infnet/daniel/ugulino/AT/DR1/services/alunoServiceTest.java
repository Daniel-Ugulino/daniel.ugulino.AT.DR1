package com.infnet.daniel.ugulino.AT.DR1.services;

import com.infnet.daniel.ugulino.AT.DR1.entity.Aluno;
import com.infnet.daniel.ugulino.AT.DR1.repository.alunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class alunoServiceTest {

    @Mock
    private alunoRepository alunoRepository;

    @InjectMocks
    private alunoService alunoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Buscar alunos")
    void testFindById() {
        Aluno aluno = new Aluno("Aluno Test","987654321","find@gmail.com","123456789","Sao Paulo");


        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

        Aluno testAluno = alunoService.findById(1L);

        assertNotNull(testAluno);
        assertThat(testAluno.getNome()).isEqualTo("Aluno Test");
        assertThat(testAluno.getCpf()).isEqualTo("987654321");
        assertThat(testAluno.getEmail()).isEqualTo("find@gmail.com");
        assertThat(testAluno.getTelefone()).isEqualTo("123456789");
        assertThat(testAluno.getEndereco()).isEqualTo("Sao Paulo");

        verify(alunoRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Listar alunos")
    void testGetAll() {
        Aluno aluno1 = new Aluno();
        aluno1.setNome("Aluno 1");

        Aluno aluno2 = new Aluno();
        aluno2.setNome("Aluno 2");

        when(alunoRepository.findAll()).thenReturn(List.of(aluno1, aluno2));

        List<Aluno> testAlunos = alunoService.getAll();

        assertEquals(2, testAlunos.size());
        assertThat(testAlunos.getFirst().getNome()).isEqualTo("Aluno 1");
        assertThat(testAlunos.get(1).getNome()).isEqualTo("Aluno 2");
        verify(alunoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Save Aluno")
    void testSave() {
        Aluno aluno = new Aluno("Aluno","123456789","info@gmail.com","987654321","Rio de Janeiro");

        when(alunoRepository.save(aluno)).thenReturn(aluno);

        Aluno testAluno = alunoService.save(aluno);

        assertThat(testAluno).isNotNull();
        assertThat(testAluno.getNome()).isEqualTo("Aluno");
        assertThat(testAluno.getCpf()).isEqualTo("123456789");
        assertThat(testAluno.getEmail()).isEqualTo("info@gmail.com");
        assertThat(testAluno.getTelefone()).isEqualTo("987654321");
        assertThat(testAluno.getEndereco()).isEqualTo("Rio de Janeiro");

        verify(alunoRepository, times(1)).save(aluno);
    }
}