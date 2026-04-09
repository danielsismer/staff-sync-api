package com.aula.__04.domain.exception;

public class ProjetoNotFound extends RuntimeException{
    public ProjetoNotFound(){
        super("Projeto não encontrado");
    }
}
