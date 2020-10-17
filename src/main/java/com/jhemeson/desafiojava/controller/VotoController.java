package com.jhemeson.desafiojava.controller;

import com.jhemeson.desafiojava.dto.ComandoAdicionarVotoDTO;
import com.jhemeson.desafiojava.dto.MessageResponseDTO;
import com.jhemeson.desafiojava.exceptions.SessaoExpiradaException;
import com.jhemeson.desafiojava.service.VotoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/voto")
public class VotoController {

    private VotoService votoService;

    @Autowired
    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    // Adicionar Voto
    @PostMapping
    public MessageResponseDTO adicionarVoto(@RequestBody ComandoAdicionarVotoDTO comandoAdicionarVotoDTO) throws NotFoundException, SessaoExpiradaException {
        return votoService.create(comandoAdicionarVotoDTO);
    }
}
