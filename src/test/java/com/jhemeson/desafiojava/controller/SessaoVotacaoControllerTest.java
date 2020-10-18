package com.jhemeson.desafiojava.controller;

import com.jhemeson.desafiojava.dto.ComandoAbrirSessaoVotacaoDTO;
import com.jhemeson.desafiojava.dto.MessageResponseDTO;
import com.jhemeson.desafiojava.dto.ResultadoSessaoVotacaoDTO;
import com.jhemeson.desafiojava.entity.Pauta;
import com.jhemeson.desafiojava.entity.SessaoVotacao;
import com.jhemeson.desafiojava.entity.Voto;
import com.jhemeson.desafiojava.repository.PautaRepository;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SessaoVotacaoControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private SessaoVotacaoRepository sessaoVotacaoRepository;
    @MockBean
    private PautaRepository pautaRepository;
    @MockBean
    private VotoRepository votoRepository;

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

        Pauta pauta = new Pauta(999L,"Pauta teste");
        SessaoVotacao sessaoVotacao = new SessaoVotacao().builder()
                .id(1L)
                .dataHoraAbertura(new Date())
                .pauta(pauta)
                .tempoDeAberturaEmSegundos(1000)
                .build();

        Voto voto1 = new Voto().builder()
                .id(1L)
                .associado("05676307307")
                .sessaoVotacao(sessaoVotacao)
                .ehVotoAprovativo(true)
                .build();

        Voto voto2 = new Voto().builder()
                .id(2L)
                .associado("05676307307")
                .sessaoVotacao(sessaoVotacao)
                .ehVotoAprovativo(true)
                .build();

        Voto voto3 = new Voto().builder()
                .id(3L)
                .associado("05676307307")
                .sessaoVotacao(sessaoVotacao)
                .ehVotoAprovativo(false)
                .build();

        List<Voto> listaVotos = new ArrayList<>();
        listaVotos.add(voto1);
        listaVotos.add(voto2);
        listaVotos.add(voto3);

        BDDMockito.when(sessaoVotacaoRepository.findById(sessaoVotacao.getId())).thenReturn(java.util.Optional.of(sessaoVotacao));
        BDDMockito.when(votoRepository.findAllBySessaoVotacao_Id(sessaoVotacao.getId())).thenReturn(listaVotos);

        ResponseEntity<ResultadoSessaoVotacaoDTO> response = restTemplate
                .getForEntity("/api/v1/sessao/1/resultado", ResultadoSessaoVotacaoDTO.class);

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
}