package com.infnet.daniel.ugulino.AT.DR1.DTO;

import com.infnet.daniel.ugulino.AT.DR1.entity.Aluno;
import com.infnet.daniel.ugulino.AT.DR1.entity.Disciplina;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDTO {
    @NotNull
    private Long aluno;

    @NotNull
    private Long disciplina;

    @NotNull
    private Double nota;

}
