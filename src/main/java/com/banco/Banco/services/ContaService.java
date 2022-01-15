package com.banco.Banco.services;

import com.banco.Banco.dtos.requests.ContaDTO;
import com.banco.Banco.dtos.requests.DepositoRequest;
import com.banco.Banco.dtos.requests.TransferirRequest;
import com.banco.Banco.dtos.responses.ContaResponse;
import com.banco.Banco.dtos.responses.MessageResponseDTO;
import com.banco.Banco.entities.Conta;
import com.banco.Banco.exceptions.ContaNotFoundException;
import com.banco.Banco.repositories.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public ContaResponse getOneConta(Long id) throws ContaNotFoundException {
        Conta conta = verifyIfContaExists(id);

        return convertContaToContaResponse(conta);
    }

    public List<ContaResponse> getAllContas() {
        List<ContaResponse> contaResponseList = new ArrayList<>();
        List<Conta> contas = contaRepository.findAll();

        contas.forEach(conta -> contaResponseList.add(convertContaToContaResponse(conta)));

        return contaResponseList;
    }

    public MessageResponseDTO updateConta(Long id, ContaDTO contaDTO) throws ContaNotFoundException {
        Conta conta = verifyIfContaExists(id);

        conta.setNome(contaDTO.getNome());
        conta.setCpf(contaDTO.getCpf());

        contaRepository.save(conta);

        return MessageResponseDTO.builder()
                .message("Alterado com sucesso")
                .build();
    }

    public void deleteConta(Long id) throws ContaNotFoundException {
        Conta conta = verifyIfContaExists(id);
        contaRepository.delete(conta);
    }

    public MessageResponseDTO depositar(Long id, DepositoRequest depositoRequest) throws ContaNotFoundException {
        Conta conta = verifyIfContaExists(id);
        conta.setSaldo(conta.getSaldo() + depositoRequest.getValor());

        contaRepository.save(conta);

        return MessageResponseDTO.builder()
                .message("Depósito realizado com sucesso")
                .build();
    }

    private ContaResponse convertContaToContaResponse(Conta conta) {
        return ContaResponse.builder()
                .contaId(conta.getContaId())
                .nome(conta.getNome())
                .cpf(conta.getCpf())
                .saldo(conta.getSaldo())
                .build();
    }

    private Conta verifyIfContaExists(Long id) throws ContaNotFoundException {
        return contaRepository.findById(id).orElseThrow(() -> new ContaNotFoundException(id));
    }

    public MessageResponseDTO transferir(Long idOrigem, TransferirRequest transferirRequest) throws Exception {
        Conta contaOrigem = verifyIfContaExists(idOrigem);
        Conta contaDestino = verifyIfContaExists(transferirRequest.getIdDestino());

        if(transferirRequest.getValor() > contaOrigem.getSaldo())
            throw new Exception("Operação não autorizada");

        contaOrigem.setSaldo(contaOrigem.getSaldo() - transferirRequest.getValor());
        contaDestino.setSaldo(contaDestino.getSaldo() + transferirRequest.getValor());

        contaRepository.save(contaDestino);
        contaRepository.save(contaOrigem);

        return MessageResponseDTO.builder()
                .message("Transferencia feita com sucesso")
                .build();
    }
}
