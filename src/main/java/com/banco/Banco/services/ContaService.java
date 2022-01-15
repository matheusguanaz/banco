package com.banco.Banco.services;

import com.banco.Banco.dtos.requests.ContaDTO;
import com.banco.Banco.dtos.responses.MessageResponseDTO;
import com.banco.Banco.entities.Conta;
import com.banco.Banco.repositories.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ContaService {

    private ContaRepository contaRepository;

    public MessageResponseDTO createConta(ContaDTO contaDTO) {
        Conta conta = new Conta();
        conta.setCpf(contaDTO.getCpf());
        conta.setNome(contaDTO.getNome());
        conta.setSaldo(0.0);

        contaRepository.save(conta);

        return MessageResponseDTO.builder()
                .message("Conta criada com sucesso")
                .build();
    }
}
