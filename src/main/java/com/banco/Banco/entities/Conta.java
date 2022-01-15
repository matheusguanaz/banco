package com.banco.Banco.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contaId;

    private String nome;

    private String cpf;

    private Double saldo;

}
