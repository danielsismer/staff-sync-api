package com.aula.__04.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aula.__04.api.dto.request.ProjetoRequestDTO;
import com.aula.__04.api.dto.response.ProjetoResponseDTO;
import com.aula.__04.application.mapper.ProjetoMapper;
import com.aula.__04.domain.entity.Projeto;
import com.aula.__04.domain.exception.ProjetoNotFound;
import com.aula.__04.infrastructure.persistence.ProjetoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjetoService {

    private final ProjetoMapper mapper;
    private final ProjetoRepository repository;

    public ProjetoResponseDTO save(ProjetoRequestDTO projetoRequestDTO) {
        return mapper.toResponse(repository.save(mapper.toEntity(projetoRequestDTO)));
    }

    public List<ProjetoResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ProjetoResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ProjetoNotFound());
    }

    public void delete(Long id) {
        Projeto projeto = repository.findById(id)
                .orElseThrow(() -> new ProjetoNotFound());

        repository.delete(projeto);
    }

    public ProjetoResponseDTO update(ProjetoRequestDTO projetoRequestDTO, Long id) {
        Projeto projeto = repository.findById(id)
                .orElseThrow(() -> new ProjetoNotFound());

        projeto.setNome(projetoRequestDTO.nome());

        return mapper.toResponse(repository.save(projeto));
    }

}
