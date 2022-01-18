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
        MessageResponseDTO result = contaService.createConta(contaDTO);
        MessageResponseDTO resultExpected = new MessageResponseDTO("Conta criada com sucesso") ;
        assertEquals(resultExpected, result);
    }

    @Test
    public void deveRetornarObjetoContaResponse_QuandoProcurarConta() throws ContaNotFoundException {
        ContaService contaService = new ContaService(contaRepository);

        ContaDTO contaDTO = new ContaDTO("teste", "86113283020");
        MessageResponseDTO contaCriada = contaService.createConta(contaDTO);

        ContaResponse resultExpected = new ContaResponse(1L, "teste", "86113283020", 0.0);
        ContaResponse result = contaService.getOneConta(1L);

        assertEquals(resultExpected, result);
    }

    @Test
    public void deveRetornarListaObjetoContaResponse_QuandoObterTodasAsContas(){
        ContaService contaService = new ContaService(contaRepository);

        ContaDTO contaDTO = new ContaDTO("teste", "86113283020");
        MessageResponseDTO contaCriada = contaService.createConta(contaDTO);

        List<ContaResponse> resultExpected = new ArrayList<>();
        ContaResponse contaResponse = new ContaResponse(1L, "teste", "86113283020", 0.0);
        resultExpected.add(contaResponse);
        List<ContaResponse> result = contaService.getAllContas();

        assertEquals(resultExpected, result);

    }

    @Test
    public void deveRetornarMensagemDeSucesso_QuandoEditarConta() throws ContaNotFoundException {
        ContaService contaService = new ContaService(contaRepository);

        ContaDTO contaDTO = new ContaDTO("teste", "86113283020");
        MessageResponseDTO contaCriada = contaService.createConta(contaDTO);

        ContaDTO contaEditada = new ContaDTO("novo teste", "86113283020");
        MessageResponseDTO resultExpected = new MessageResponseDTO("Alterado com sucesso") ;
        MessageResponseDTO result = contaService.updateConta(1L,contaEditada);

        assertEquals(resultExpected, result);
    }

    @Test
    public void deveRetornarMensagemDeSucesso_QuandoFizerDeposito() throws ContaNotFoundException {
        ContaService contaService = new ContaService(contaRepository);

        ContaDTO contaDTO = new ContaDTO("teste", "86113283020");
        MessageResponseDTO contaCriada = contaService.createConta(contaDTO);

        MessageResponseDTO resultExpected = new MessageResponseDTO("Depósito realizado com sucesso");
        MessageResponseDTO result = contaService.depositar(1L, new DepositoRequest(200.0));

        assertEquals(resultExpected, result);
    }

    @Test
    public void deveRetornarMensagemDeSucesso_QuandoFizerTransferencia() throws Exception {
        ContaService contaService = new ContaService(contaRepository);

        ContaDTO contaDTO = new ContaDTO("teste", "86113283020");
        ContaDTO contaDTO_1 = new ContaDTO("teste2", "56925669070");
        MessageResponseDTO contaCriada = contaService.createConta(contaDTO);
        MessageResponseDTO contaCriada_1 = contaService.createConta(contaDTO_1);

        MessageResponseDTO depositar = contaService.depositar(1L, new DepositoRequest(200.0));
        ResponseEntity<MessageResponseDTO> resultExpected = ResponseEntity.ok(MessageResponseDTO.builder()
                .message("Transferencia feita com sucesso")
                .build());
        ResponseEntity<MessageResponseDTO> result = contaService.transferir(1L, new TransferirRequest(2L, 200.0));

        assertEquals(resultExpected, result);
    }
}
