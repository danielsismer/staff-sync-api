package com.aula.__04.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aula.__04.api.dto.request.AssentoRequestDTO;
import com.aula.__04.api.dto.response.AssentoResponseDTO;
import com.aula.__04.application.mapper.AssentoMapper;
import com.aula.__04.domain.entity.Assento;
import com.aula.__04.domain.exception.AssentoNotFound;
import com.aula.__04.infrastructure.persistence.AssentoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssentoService {

    private final AssentoMapper mapper;
    private final AssentoRepository repository;

    public AssentoResponseDTO save(AssentoRequestDTO assentoRequestDTO) {
        return mapper.toResponse(repository.save(mapper.toEntity(assentoRequestDTO)));
    }

    public List<AssentoResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public AssentoResponseDTO findById(Long id) {
        return mapper.toResponse(repository.findById(id)
                .orElseThrow(() -> new AssentoNotFound()));

    }

    public void delete(Long id) {
        Assento assento = repository.findById(id)
                .orElseThrow(() -> new AssentoNotFound());

        repository.delete(assento);
    }

    public AssentoResponseDTO update(AssentoRequestDTO assentoRequestDTO, Long id) {
        Assento assento = repository.findById(id)
                .orElseThrow(() -> new AssentoNotFound());

        assento.setCodigo(assentoRequestDTO.codigo());
        return mapper.toResponse(repository.save(assento));
    }

}
