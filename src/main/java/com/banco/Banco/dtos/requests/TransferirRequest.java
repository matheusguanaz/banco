package com.banco.Banco.dtos.requests;

import lombok.Data;

@Data
public class TransferirRequest {

    private Long idDestino;

    private Double valor;
}
