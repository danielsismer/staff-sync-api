package com.aula.__04.application.mapper;

import org.springframework.stereotype.Component;

import com.aula.__04.api.dto.request.FuncionarioRequestDTO;
import com.aula.__04.api.dto.response.FuncionarioResponseDTO;
import com.aula.__04.domain.entity.Funcionario;

@Component
public class FuncionarioMapper {

     public Funcionario toEntity(FuncionarioRequestDTO funcionarioRequestDTO){
        return new Funcionario(
            funcionarioRequestDTO.nome()
        );
     }

     public FuncionarioResponseDTO toResponse(Funcionario funcionario){

        return new FuncionarioResponseDTO(
            funcionario.getId(),
            funcionario.getNome(),
            funcionario.getAssento() != null ? funcionario.getAssento().getId() : null,
            funcionario.getProjetos() != null ? funcionario.getProjetos() : null
        );
     }
    
}
