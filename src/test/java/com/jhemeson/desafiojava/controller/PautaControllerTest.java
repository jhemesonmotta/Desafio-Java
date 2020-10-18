package com.jhemeson.desafiojava.controller;

import com.jhemeson.desafiojava.dto.MessageResponseDTO;
import com.jhemeson.desafiojava.dto.PautaDTO;
import com.jhemeson.desafiojava.entity.Pauta;
import com.jhemeson.desafiojava.repository.PautaRepository;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PautaControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private PautaRepository pautaRepository;

    @Test
    void cadastrarPauta() {
        PautaDTO pautaDTO = new PautaDTO().builder().nome("Pauta Teste").build();
        Pauta pauta = new Pauta().builder()
                .nome("Pauta Teste").build();

        BDDMockito.when(pautaRepository.save(pauta)).thenReturn(pauta);

        ResponseEntity<MessageResponseDTO> response = restTemplate
                .postForEntity("/api/v1/pauta", pautaDTO, MessageResponseDTO.class);

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
}