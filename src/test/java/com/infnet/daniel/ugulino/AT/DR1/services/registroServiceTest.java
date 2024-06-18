package com.infnet.daniel.ugulino.AT.DR1.services;

import com.infnet.daniel.ugulino.AT.DR1.entity.Aluno;
import com.infnet.daniel.ugulino.AT.DR1.entity.Disciplina;
import com.infnet.daniel.ugulino.AT.DR1.entity.Registro;
import com.infnet.daniel.ugulino.AT.DR1.repository.alunoRepository;
import com.infnet.daniel.ugulino.AT.DR1.repository.disciplinaRepository;
import com.infnet.daniel.ugulino.AT.DR1.repository.registroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class registroServiceTest {

    @Mock
    private registroRepository registroRepository;

    @Mock
    private alunoRepository alunoRepository;

    @Mock
    private disciplinaRepository disciplinaRepository;

    @InjectMocks
    private registroService regsitroService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Salvar Registro")
    void save() {
        Disciplina disciplina = new Disciplina(12345L,"Java");
        Aluno aluno = new Aluno("Aluno Test","987654321","find@gmail.com","Sao Paulo","123456789");
        Registro registro = new Registro(disciplina,aluno,8.0);

        when(disciplinaRepository.save(disciplina)).thenReturn(disciplina);
        when(alunoRepository.save(aluno)).thenReturn(aluno);
        when(registroRepository.save(registro)).thenReturn(registro);

        Registro testRegistro = regsitroService.save(registro);

        assertThat(testRegistro.getNota()).isEqualTo(8.0);
        assertThat(testRegistro.getAluno().getNome()).isEqualTo("Aluno Test");
        assertThat(testRegistro.getAluno().getCpf()).isEqualTo("987654321");
        assertThat(testRegistro.getDisciplina().getNome()).isEqualTo("Java");
        assertThat(testRegistro.getDisciplina().getCodigo()).isEqualTo(12345L);
    }

    @Test
    @DisplayName("Buscar Registros")
    void findById() {
        Disciplina disciplina = new Disciplina(12345L,"Java");
        Aluno aluno = new Aluno("Aluno Test","987654321","find@gmail.com","Sao Paulo","123456789");
        Registro registro = new Registro(disciplina,aluno,8.0);

        when(registroRepository.findById(1L)).thenReturn(Optional.of(registro));

        Registro testRegistro = regsitroService.findById(1L);

        assertThat(testRegistro).isNotNull();
        assertThat(testRegistro.getNota()).isEqualTo(8.0);
        assertThat(testRegistro.getAluno().getNome()).isEqualTo("Aluno Test");
        assertThat(testRegistro.getAluno().getCpf()).isEqualTo("987654321");
        assertThat(testRegistro.getDisciplina().getNome()).isEqualTo("Java");
        assertThat(testRegistro.getDisciplina().getCodigo()).isEqualTo(12345L);
        verify(registroRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Listar Registros")
    void getAll() {
        Disciplina disciplina1 = new Disciplina(12345L,"Java");
        Aluno aluno1 = new Aluno("Aluno Test 1","123456789","find@gmail.com","Sao Paulo","987654321");
        Registro registro1 = new Registro(disciplina1,aluno1,8.0);

        Disciplina disciplina2 = new Disciplina(54321L,"Python");
        Aluno aluno2 = new Aluno("Aluno Test 2","123456789","test2@gmail.com","Rio de Janeiro","987654321");
        Registro registro2 = new Registro(disciplina2,aluno2,2.0);

        when(registroRepository.findAll()).thenReturn(List.of(registro1, registro2));

        List<Registro> testRegistros = regsitroService.getAll();

        assertEquals(2, testRegistros.size());
        assertThat(testRegistros.getFirst().getNota()).isEqualTo(8.0);
        assertThat(testRegistros.getFirst().getAluno().getNome()).isEqualTo("Aluno Test 1");
        assertThat(testRegistros.getFirst().getDisciplina().getNome()).isEqualTo("Java");

        assertThat(testRegistros.get(1).getNota()).isEqualTo(2.0);
        assertThat(testRegistros.get(1).getAluno().getNome()).isEqualTo("Aluno Test 2");
        assertThat(testRegistros.get(1).getDisciplina().getNome()).isEqualTo("Python");
        verify(registroRepository, times(2)).findAll();
    }

    @Test
    @DisplayName("Buscar Aprovados")
    void getAprovados() {
        Disciplina disciplina = new Disciplina(12345L,"Java");
        Aluno aluno = new Aluno("Aluno Test 1","123456789","find@gmail.com","Sao Paulo","987654321");
        Registro registro = new Registro(disciplina,aluno,8.0);

        when(registroRepository.findByDisciplinaCodigoAndNotaGreaterThanEqual(12345L,7.0)).thenReturn(List.of(registro));

        List<Registro> testRegistros = regsitroService.getAprovados(disciplina.getCodigo());

        assertEquals(1, testRegistros.size());
        assertThat(testRegistros.getFirst().getNota()).isEqualTo(8.0);
        assertThat(testRegistros.getFirst().getAluno().getNome()).isEqualTo("Aluno Test 1");
        assertThat(testRegistros.getFirst().getDisciplina().getNome()).isEqualTo("Java");
        assertThat(testRegistros.getFirst().getDisciplina().getCodigo()).isEqualTo(12345L);

        verify(registroRepository, times(1)).findByDisciplinaCodigoAndNotaGreaterThanEqual(12345L,7.0);
    }

    @Test
    @DisplayName("Buscar Reprovados")
    void getReprovados() {
        Disciplina disciplina = new Disciplina(12345L,"Java");
        Aluno aluno = new Aluno("Aluno Test","123456789","test2@gmail.com","Rio de Janeiro","987654321");
        Registro registro = new Registro(disciplina,aluno,2.0);

        when(registroRepository.findByDisciplinaCodigoAndNotaLessThan(12345L,7.0)).thenReturn(List.of(registro));

        List<Registro> testRegistros = regsitroService.getReprovados(disciplina.getCodigo());

        assertEquals(1, testRegistros.size());
        assertThat(testRegistros.getFirst().getNota()).isEqualTo(2.0);
        assertThat(testRegistros.getFirst().getAluno().getNome()).isEqualTo("Aluno Test");
        assertThat(testRegistros.getFirst().getDisciplina().getNome()).isEqualTo("Java");
        assertThat(testRegistros.getFirst().getDisciplina().getCodigo()).isEqualTo(12345L);

        verify(registroRepository, times(1)).findByDisciplinaCodigoAndNotaLessThan(12345L,7.0);

    }
}