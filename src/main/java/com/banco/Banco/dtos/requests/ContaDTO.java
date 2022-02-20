package com.banco.Banco.dtos.requests;


import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTO {

    @NotEmpty
    private String nome;

    @NotEmpty
    @CPF
    private String cpf;

}
