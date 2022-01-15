package com.banco.Banco.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContaNotFoundException extends Exception{
    public ContaNotFoundException(Long id){
        super("Conta com o id " + id + "não encontrada");
    }
}
