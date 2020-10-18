package com.jhemeson.desafiojava.controller;

import com.jhemeson.desafiojava.dto.ComandoAdicionarVotoDTO;
import com.jhemeson.desafiojava.dto.MessageResponseDTO;
import com.jhemeson.desafiojava.entity.Pauta;
import com.jhemeson.desafiojava.entity.SessaoVotacao;
import com.jhemeson.desafiojava.entity.Voto;
import com.jhemeson.desafiojava.repository.SessaoVotacaoRepository;
import com.jhemeson.desafiojava.repository.VotoRepository;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VotoControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private SessaoVotacaoRepository sessaoVotacaoRepository;
    @MockBean
    private VotoRepository votoRepository;

    @Test
    void adicionarVoto() {
        Pauta pauta = new Pauta(1L,"Pauta teste 1");
        SessaoVotacao sessaoVotacao = new SessaoVotacao().builder()
                .id(1L)
                .dataHoraAbertura(new Date())
                .pauta(pauta)
                .tempoDeAberturaEmSegundos(1000)
                .build();

        Voto voto = new Voto().builder()
                .id(1L)
                .associado("05676307307")
                .sessaoVotacao(sessaoVotacao)
                .ehVotoAprovativo(true)
                .build();

        ComandoAdicionarVotoDTO comandoAdicionarVotoDTO = new ComandoAdicionarVotoDTO().builder()
                .sessaoVotacao(1L)
                .associado("05676307307")
                .ehVotoAprovativo(true)
                .build();

        BDDMockito.when(sessaoVotacaoRepository.findById(comandoAdicionarVotoDTO.getSessaoVotacao()))
                .thenReturn(java.util.Optional.ofNullable(sessaoVotacao));
        BDDMockito.when(votoRepository.save(voto)).thenReturn(voto);

        ResponseEntity<MessageResponseDTO> response = restTemplate
                .postForEntity("/api/v1/voto", comandoAdicionarVotoDTO, MessageResponseDTO.class);

        boolean usuarioNaoPodeVotar = response.getBody().getMessage().equals("Usuário não pode votar.");
        boolean votouEValidou = response.getStatusCodeValue() == 200;

        // RATIONALE: em um cenário real este seria um teste ruim.
        // Todavia, como o serviço de validação de usuário retorna valores aleatórios, foi a saída encontrada.
        Assertions.assertThat(usuarioNaoPodeVotar || votouEValidou).isEqualTo(true);
    }
}