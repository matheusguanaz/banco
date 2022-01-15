package com.banco.Banco.controllers;

import com.banco.Banco.dtos.requests.ContaDTO;
import com.banco.Banco.dtos.responses.ContaResponse;
import com.banco.Banco.dtos.responses.MessageResponseDTO;
import com.banco.Banco.exceptions.ContaNotFoundException;
import com.banco.Banco.services.ContaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conta")
@AllArgsConstructor(onConstructor = @__(@Autowired))

public class ContaController {

    private ContaService contaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createConta(@RequestBody ContaDTO contaDTO){
        return contaService.createConta(contaDTO);
    }

    @GetMapping("/{id}")
    public ContaResponse getOneConta(@PathVariable Long id) throws ContaNotFoundException {
        return  contaService.getOneConta(id);
    }
}
