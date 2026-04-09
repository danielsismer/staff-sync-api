package com.aula.__04.application.mapper;

import org.springframework.stereotype.Component;

import com.aula.__04.api.dto.request.ProjetoRequestDTO;
import com.aula.__04.api.dto.response.ProjetoResponseDTO;
import com.aula.__04.domain.entity.Projeto;

@Component
public class ProjetoMapper {
    
    public Projeto toEntity(ProjetoRequestDTO projetoRequestDTO){
        return new Projeto(
            projetoRequestDTO.nome()
        );
    }

    public ProjetoResponseDTO toResponse(Projeto projeto){
        return new ProjetoResponseDTO(
            projeto.getId(),
            projeto.getNome(),
            projeto.getFuncionarios() != null ? projeto.getFuncionarios() : null
        );
    }

}
