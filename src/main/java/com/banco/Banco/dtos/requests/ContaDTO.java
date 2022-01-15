package com.banco.Banco.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTO {

    @NotEmpty
    private String nome;

    @NotEmpty
    private String cpf;

}
