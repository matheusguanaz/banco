package com.banco.Banco.controllers;

import com.banco.Banco.dtos.requests.ContaDTO;
import com.banco.Banco.dtos.requests.DepositoRequest;
import com.banco.Banco.dtos.responses.ContaResponse;
import com.banco.Banco.dtos.responses.MessageResponseDTO;
import com.banco.Banco.exceptions.ContaNotFoundException;
import com.banco.Banco.services.ContaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/conta")
@AllArgsConstructor(onConstructor = @__(@Autowired))

public class ContaController {

    private ContaService contaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createConta(@RequestBody @Valid ContaDTO contaDTO){
        return contaService.createConta(contaDTO);
    }

    @GetMapping("/{id}")
    public ContaResponse getOneConta(@PathVariable Long id) throws ContaNotFoundException {
        return  contaService.getOneConta(id);
    }

    @GetMapping
    public List<ContaResponse> getAllContas(){
        return contaService.getAllContas();
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateConta(@PathVariable Long id, @RequestBody @Valid ContaDTO contaDTO) throws ContaNotFoundException {
        return contaService.updateConta(id, contaDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConta(@PathVariable Long id) throws ContaNotFoundException {
        contaService.deleteConta(id);
    }

    @PatchMapping("/depositar/{id}")
    public MessageResponseDTO depositar(@PathVariable Long id, @RequestBody @Valid DepositoRequest depositoRequest) throws ContaNotFoundException {
        return contaService.depositar(id, depositoRequest);
    }
}
