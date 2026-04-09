package com.aula.__04.api.dto.response;

import java.util.List;

import com.aula.__04.domain.entity.Funcionario;

public record ProjetoResponseDTO(
    Long id,
    String nome,
    List<Funcionario> funcionarios
) {

} 
    
