package com.banco.Banco.dtos.requests;

import lombok.Data;

import javax.validation.constraints.DecimalMax;


@Data
public class DepositoRequest {

    @DecimalMax("2000.0")
    private Double valor;
}
