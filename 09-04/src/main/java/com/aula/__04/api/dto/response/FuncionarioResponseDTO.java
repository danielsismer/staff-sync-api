package com.aula.__04.api.dto.response;

import java.util.List;

import com.aula.__04.domain.entity.Projeto;

public record FuncionarioResponseDTO( 
    Long id,
    String nome,
    Long assentoId,
    List<Projeto> projetos
){
}
