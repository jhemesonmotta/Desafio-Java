package com.jhemeson.desafiojava.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoDTO {
    private SessaoVotacaoDTO sessaoVotacao;
    private String associado;
    private boolean ehVotoAprovativo;
}
