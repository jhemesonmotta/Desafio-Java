package com.jhemeson.desafiojava.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoSessaoVotacaoDTO {
    private Long id;
    private PautaDTO pauta;
    private Long votosFavoraveis;
    private Long votosContra;
}
