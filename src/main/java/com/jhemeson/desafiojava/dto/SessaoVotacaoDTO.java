package com.jhemeson.desafiojava.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessaoVotacaoDTO {
    private Long id;
    private PautaDTO pauta;
    private Integer tempoDeAberturaEmSegundos;
}
