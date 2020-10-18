package com.jhemeson.desafiojava.controller;

import com.jhemeson.desafiojava.dto.ComandoAbrirSessaoVotacaoDTO;
import com.jhemeson.desafiojava.dto.MessageResponseDTO;
import com.jhemeson.desafiojava.entity.Pauta;
import com.jhemeson.desafiojava.entity.SessaoVotacao;
import com.jhemeson.desafiojava.repository.PautaRepository;
import com.jhemeson.desafiojava.repository.SessaoVotacaoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SessaoVotacaoControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private SessaoVotacaoRepository sessaoVotacaoRepository;
    @MockBean
    private PautaRepository pautaRepository;

    @Test
    void abrirSessao() {
        Pauta pauta = new Pauta(999L,"Pauta teste");

        ComandoAbrirSessaoVotacaoDTO comandoAbrirSessaoVotacaoDTO = new ComandoAbrirSessaoVotacaoDTO().builder()
                .pauta(pauta.getId())
                .tempoDeAberturaEmSegundos(1000)
                .build();

        SessaoVotacao sessaoVotacao = new SessaoVotacao().builder()
                .dataHoraAbertura(new Date())
                .pauta(pauta)
                .tempoDeAberturaEmSegundos(1000)
                .build();

        BDDMockito.when(pautaRepository.save(pauta)).thenReturn(pauta);

        BDDMockito.when(pautaRepository.findById(pauta.getId())).thenReturn(java.util.Optional.of(pauta));

        BDDMockito.when(sessaoVotacaoRepository.save(sessaoVotacao)).thenReturn(sessaoVotacao);

        ResponseEntity<MessageResponseDTO> response = restTemplate
                .postForEntity("/api/v1/sessao", comandoAbrirSessaoVotacaoDTO, MessageResponseDTO.class);

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void buscarResultadoSessaoVotacao() {

    }
}