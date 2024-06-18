package com.infnet.daniel.ugulino.AT.DR1.services;

import com.infnet.daniel.ugulino.AT.DR1.entity.Disciplina;
import com.infnet.daniel.ugulino.AT.DR1.repository.disciplinaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class disciplinaServiceTest {

    @Mock
    private disciplinaRepository disciplinaRepository;

    @InjectMocks
    private disciplinaService disciplinaService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Salvar Disciplina")
    void save() {
        Disciplina disciplina = new Disciplina(12345L,"Java");

        when(disciplinaRepository.save(disciplina)).thenReturn(disciplina);

        Disciplina testDisciplina = disciplinaService.save(disciplina);

        assertThat(testDisciplina).isNotNull();
        assertThat(testDisciplina.getNome()).isEqualTo("Java");
        assertThat(testDisciplina.getCodigo()).isEqualTo(12345L);

        Mockito.verify(disciplinaRepository, times(1)).save(disciplina);
    }

    @Test
    @DisplayName("Buscar Disciplina")
    void findById() {
        Disciplina disciplina = new Disciplina(54321L,"Python");

        when(disciplinaRepository.findById(1L)).thenReturn(Optional.of(disciplina));

        Disciplina testDisciplina = disciplinaService.findById(1L);

        assertNotNull(testDisciplina);
        assertThat(testDisciplina.getNome()).isEqualTo("Python");
        assertThat(testDisciplina.getCodigo()).isEqualTo(54321L);
        Mockito.verify(disciplinaRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Listar Disciplina")
    void getAll() {
        Disciplina disciplina1 = new Disciplina(98765L,"Disciplina 1");
        Disciplina disciplina2 = new Disciplina(56789L,"Disciplina 2");

        when(disciplinaRepository.findAll()).thenReturn(List.of(disciplina1, disciplina2));

        List<Disciplina> testDisciplinas = disciplinaService.getAll();

        assertEquals(2, testDisciplinas.size());
        assertThat(testDisciplinas.getFirst().getNome()).isEqualTo("Disciplina 1");
        assertThat(testDisciplinas.getFirst().getCodigo()).isEqualTo(98765L);

        assertThat(testDisciplinas.get(1).getNome()).isEqualTo("Disciplina 2");
        assertThat(testDisciplinas.get(1).getCodigo()).isEqualTo(56789L);

        verify(disciplinaRepository, times(1)).findAll();
    }
}