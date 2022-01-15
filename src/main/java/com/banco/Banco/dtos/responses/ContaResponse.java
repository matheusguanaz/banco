package com.banco.Banco.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ContaResponse {

    private Long contaId;

    private String nome;

    private String cpf;

    private Double saldo;
}
