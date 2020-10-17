package com.jhemeson.desafiojava.controller;

import com.jhemeson.desafiojava.dto.MessageResponseDTO;
import com.jhemeson.desafiojava.dto.PautaDTO;
import com.jhemeson.desafiojava.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pauta")
public class PautaController {

    private PautaService pautaService;

    @Autowired
    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    // CADASTRAR PAUTA
    @PostMapping
    public MessageResponseDTO create(@RequestBody PautaDTO pautaDTO) {
        return pautaService.create(pautaDTO);
    }

}
