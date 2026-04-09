package com.aula.__04.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aula.__04.api.dto.request.FuncionarioRequestDTO;
import com.aula.__04.api.dto.response.FuncionarioResponseDTO;
import com.aula.__04.application.service.FuncionarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService service;

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> save(@RequestBody FuncionarioRequestDTO funcionarioRequest) {
        return ResponseEntity
                .status(201)
                .body(service.save(funcionarioRequest));
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> findAll() {
        return ResponseEntity
                .status(200)
                .body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity
                .status(204)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> update(@RequestBody @Valid FuncionarioRequestDTO funcionarioRequestDTO, @PathVariable Long id){
        return ResponseEntity
        .status(200)
        .body(service.update(funcionarioRequestDTO, id));
    }


    @PostMapping("/assento/{funcionarioId}/{assentoId}")
    public ResponseEntity<FuncionarioResponseDTO> associarAssento(@PathVariable Long funcionarioId, @PathVariable Long assentoId){
        return ResponseEntity
        .status(200)
        .body(service.associarAssento(funcionarioId, assentoId));
    }

    @PostMapping("/projeto/{funcionarioId}")
    public ResponseEntity<FuncionarioResponseDTO> associarProjeto(@RequestBody @Valid List<Long> projetos, @PathVariable Long funcionarioId){
        return ResponseEntity
        .status(200)
        .body(service.associarProjeto(projetos, funcionarioId));
    }

}
