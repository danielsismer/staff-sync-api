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

import com.aula.__04.api.dto.request.AssentoRequestDTO;
import com.aula.__04.api.dto.response.AssentoResponseDTO;
import com.aula.__04.application.service.AssentoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/assentos")
@RequiredArgsConstructor
public class AssentoController {

    private final AssentoService service;

    @PostMapping
    public ResponseEntity<AssentoResponseDTO> save(@RequestBody @Valid AssentoRequestDTO assentoRequestDTO) {
        return ResponseEntity
                .status(201)
                .body(service.save(assentoRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<AssentoResponseDTO>> findAll() {
        return ResponseEntity
                .status(200)
                .body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssentoResponseDTO> findById(@PathVariable Long id) {
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
    public ResponseEntity<AssentoResponseDTO> update(@RequestBody @Valid AssentoRequestDTO assentoRequestDTO,
            @PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(service.update(assentoRequestDTO, id));
    }

}
