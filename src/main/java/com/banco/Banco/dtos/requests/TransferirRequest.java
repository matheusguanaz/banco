package com.banco.Banco.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferirRequest {

    private Long idDestino;

    private Double valor;
}
