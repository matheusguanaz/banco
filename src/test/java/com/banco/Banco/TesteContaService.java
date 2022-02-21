package com.banco.Banco;


import com.banco.Banco.dtos.requests.ContaDTO;
import com.banco.Banco.dtos.requests.DepositoRequest;
import com.banco.Banco.dtos.requests.TransferirRequest;
import com.banco.Banco.dtos.responses.ContaResponse;
import com.banco.Banco.dtos.responses.MessageResponseDTO;
import com.banco.Banco.exceptions.ContaNotFoundException;
import com.banco.Banco.repositories.ContaRepository;
import com.banco.Banco.services.ContaService;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TesteContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Test
    public void deveRetornarMensagemDeSucesso_QuandoCriarConta() throws ContaNotFoundException {
        ContaService contaService = new ContaService(contaRepository);

        ContaDTO contaDTO = new ContaDTO("teste", "86113283020");
        ResponseEntity<MessageResponseDTO> result = contaService.createConta(contaDTO);
        ResponseEntity<MessageResponseDTO> resultExpected = ResponseEntity
                .ok(MessageResponseDTO.builder()
                        .message("Conta criada com sucesso")
                        .build());

        assertEquals(resultExpected, result);
    }

    @Test
    public void deveRetornarObjetoContaResponse_QuandoProcurarConta() throws ContaNotFoundException {
        ContaService contaService = new ContaService(contaRepository);

        ContaDTO contaDTO = new ContaDTO("teste", "86113283020");
        ResponseEntity<MessageResponseDTO> contaCriada = contaService.createConta(contaDTO);

        ResponseEntity<ContaResponse> resultExpected = ResponseEntity.ok(
                ContaResponse.builder()
                        .contaId(1L)
                        .nome("teste")
                        .cpf("86113283020")
                        .saldo(0.0).build());

        ResponseEntity<ContaResponse> result = contaService.getOneConta(1L);

        assertEquals(resultExpected, result);
    }

    @Test
    public void deveRetornarListaObjetoContaResponse_QuandoObterTodasAsContas(){
        ContaService contaService = new ContaService(contaRepository);

        ContaDTO contaDTO = new ContaDTO("teste", "86113283020");
        contaService.createConta(contaDTO);

        List<ContaResponse> contaResponseList = new ArrayList<>(
                Arrays.asList(
                        new ContaResponse(
                                1L,
                                "teste",
                                "86113283020",
                                0.0
                        )
                ));

        ResponseEntity<List<ContaResponse>> resultExpected = ResponseEntity.ok(contaResponseList);
        ResponseEntity<List<ContaResponse>> result = contaService.getAllContas();

        assertEquals(resultExpected, result);

    }

    @Test
    public void deveRetornarMensagemDeSucesso_QuandoEditarConta() throws ContaNotFoundException {
        ContaService contaService = new ContaService(contaRepository);

        contaService.createConta(new ContaDTO("teste", "86113283020"));

        ContaDTO contaEditada = new ContaDTO("novo teste", "86113283020");
        ResponseEntity<MessageResponseDTO> resultExpected = ResponseEntity.ok(
                MessageResponseDTO
                        .builder()
                        .message("Alterado com sucesso")
                        .build()
        );
        ResponseEntity<MessageResponseDTO> result = contaService.updateConta(1L,contaEditada);

        assertEquals(resultExpected, result);
    }

    @Test
    public void deveRetornarMensagemDeSucesso_QuandoFizerDeposito() throws ContaNotFoundException {
        ContaService contaService = new ContaService(contaRepository);

        contaService.createConta(new ContaDTO("teste", "86113283020"));

        ResponseEntity<MessageResponseDTO> resultExpected = ResponseEntity.ok(
                MessageResponseDTO
                        .builder()
                        .message("Depósito realizado com sucesso")
                        .build()
        );
        ResponseEntity<MessageResponseDTO> result = contaService.depositar(1L, new DepositoRequest(200.0));

        assertEquals(resultExpected, result);
    }

    @Test
    public void deveRetornarMensagemDeSucesso_QuandoFizerTransferencia() throws Exception {
        ContaService contaService = new ContaService(contaRepository);

        contaService.createConta(new ContaDTO("teste", "86113283020"));
        contaService.createConta(new ContaDTO("teste2", "56925669070"));

        ResponseEntity<MessageResponseDTO> depositar = contaService.depositar(1L, new DepositoRequest(200.0));
        ResponseEntity<MessageResponseDTO> resultExpected = ResponseEntity.ok(
                MessageResponseDTO.builder()
                .message("Transferencia feita com sucesso")
                .build()
        );
        ResponseEntity<MessageResponseDTO> result = contaService.transferir(1L, new TransferirRequest(2L, 200.0));

        assertEquals(resultExpected, result);
    }
}
