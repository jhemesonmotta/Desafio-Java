package com.jhemeson.desafiojava.controller;

import com.jhemeson.desafiojava.dto.ComandoAbrirSessaoVotacaoDTO;
import com.jhemeson.desafiojava.dto.MessageResponseDTO;
import com.jhemeson.desafiojava.dto.PautaDTO;
import com.jhemeson.desafiojava.service.SessaoVotacaoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sessao")
public class SessaoVotacaoController {

    private SessaoVotacaoService sessaoVotacaoService;

    @Autowired
    public SessaoVotacaoController(SessaoVotacaoService sessaoVotacaoService) {
        this.sessaoVotacaoService = sessaoVotacaoService;
    }

    // Abrir sessão
    @PostMapping
    public MessageResponseDTO abrirSessao(@RequestBody ComandoAbrirSessaoVotacaoDTO comandoAbrirSessaoVotacaoDTO) throws NotFoundException {
        return sessaoVotacaoService.create(comandoAbrirSessaoVotacaoDTO);
    }
}
