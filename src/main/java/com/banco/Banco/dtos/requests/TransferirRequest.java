package com.banco.Banco.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferirRequest {

    private Long idDestino;

    @DecimalMin("0.0")
    @Positive
    private Double valor;
}
