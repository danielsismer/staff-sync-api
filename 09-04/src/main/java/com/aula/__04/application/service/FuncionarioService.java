package com.aula.__04.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aula.__04.api.dto.request.FuncionarioRequestDTO;
import com.aula.__04.api.dto.response.FuncionarioResponseDTO;
import com.aula.__04.application.mapper.FuncionarioMapper;
import com.aula.__04.domain.entity.Assento;
import com.aula.__04.domain.entity.Funcionario;
import com.aula.__04.domain.entity.Projeto;
import com.aula.__04.domain.exception.AssentoNotFound;
import com.aula.__04.domain.exception.FuncionarioNotFound;
import com.aula.__04.infrastructure.persistence.AssentoRepository;
import com.aula.__04.infrastructure.persistence.FuncionarioRepository;
import com.aula.__04.infrastructure.persistence.ProjetoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioMapper mapper;
    private final FuncionarioRepository repository;
    private final AssentoRepository assentoRepository;
    private final ProjetoRepository projetoRepository;

    public FuncionarioResponseDTO save(FuncionarioRequestDTO funcionarioRequestDTO) {
        return mapper.toResponse(repository.save(mapper.toEntity(funcionarioRequestDTO)));
    }

    public List<FuncionarioResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public FuncionarioResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new FuncionarioNotFound());
    }

    public void delete(Long id) {
        Funcionario funcionario = repository.findById(id)
                .orElseThrow(() -> new FuncionarioNotFound());

        repository.delete(funcionario);
    }

    public FuncionarioResponseDTO update(FuncionarioRequestDTO funcionarioRequestDTO, Long id) {
        Funcionario funcionario = repository.findById(id)
                .orElseThrow(() -> new FuncionarioNotFound());

        funcionario.setNome(funcionarioRequestDTO.nome());

        return mapper.toResponse(repository.save(funcionario));
    }

    @Transactional  
    public FuncionarioResponseDTO associarAssento(Long funcionario_id, Long assento_id) {
        Assento assento = assentoRepository.findById(assento_id)
                .orElseThrow(() -> new AssentoNotFound());

        Funcionario funcionario = repository.findById(funcionario_id)
                .orElseThrow(() -> new FuncionarioNotFound());

        funcionario.setAssento(assento);

        return mapper.toResponse(repository.save(funcionario));
    }

    public FuncionarioResponseDTO associarProjeto(List<Long> projetosIds, Long funcionario_id) {

        Funcionario funcionario = repository.findById(funcionario_id)
                .orElseThrow(() -> new FuncionarioNotFound());
    
        List<Projeto> projetosParaAssociar = projetoRepository.findAllById(projetosIds);
    
        List<Long> idsEncontrados = projetosParaAssociar
                .stream()
                .map(Projeto::getId)
                .toList();
    
        List<Long> idsNaoEncontrados = projetosIds
                .stream()
                .filter(id -> !idsEncontrados.contains(id))
                .toList();
    
        if (!idsNaoEncontrados.isEmpty()) {
            throw new EntityNotFoundException("IDs não encontrados: " + idsNaoEncontrados);
        }
    
        funcionario.getProjetos().addAll(projetosParaAssociar);
    
        return mapper.toResponse(repository.save(funcionario));
    }

}
