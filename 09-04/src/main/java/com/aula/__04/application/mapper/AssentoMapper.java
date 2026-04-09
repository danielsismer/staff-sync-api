package com.aula.__04.application.mapper;

import org.springframework.stereotype.Component;

import com.aula.__04.api.dto.request.AssentoRequestDTO;
import com.aula.__04.api.dto.response.AssentoResponseDTO;
import com.aula.__04.domain.entity.Assento;

@Component
public class AssentoMapper {
    
    public Assento toEntity(AssentoRequestDTO assentoRequestDTO){
        return new Assento(
            assentoRequestDTO.codigo()
        );
    }

    public AssentoResponseDTO toResponse(Assento assento) {
        return new AssentoResponseDTO(
            assento.getId(),
            assento.getCodigo(),
            assento.getFuncionario() != null ? assento.getFuncionario().getNome() : null
        );
    }

}
