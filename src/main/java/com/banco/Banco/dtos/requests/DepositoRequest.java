package com.banco.Banco.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.DecimalMax;


@Data
@AllArgsConstructor
public class DepositoRequest {

    @DecimalMax("2000.0")
    private Double valor;
}
