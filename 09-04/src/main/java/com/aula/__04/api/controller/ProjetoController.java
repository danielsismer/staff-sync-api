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

import com.aula.__04.api.dto.request.ProjetoRequestDTO;
import com.aula.__04.api.dto.response.ProjetoResponseDTO;
import com.aula.__04.application.service.ProjetoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/projetos")
@RequiredArgsConstructor
public class ProjetoController {

    private final ProjetoService service;

    @PostMapping
    public ResponseEntity<ProjetoResponseDTO> save(@RequestBody @Valid ProjetoRequestDTO projetoRequestDTO) {
        return ResponseEntity
                .status(201)
                .body(service.save(projetoRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<ProjetoResponseDTO>> findAll() {
        return ResponseEntity
                .status(200)
                .body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity
        .status(200)
        .body(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity
        .status(204)
        .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetoResponseDTO> update(@RequestBody @Valid ProjetoRequestDTO projetoRequestDTO, @PathVariable Long id){
        return ResponseEntity
        .status(200)
        .body(service.update(projetoRequestDTO, id));
    }
}
