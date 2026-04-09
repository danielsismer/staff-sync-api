package com.aula.__04.domain.exception;

public class FuncionarioNotFound extends RuntimeException{
    public FuncionarioNotFound(){
        super("Funcionário não encontrado");
    }
}
