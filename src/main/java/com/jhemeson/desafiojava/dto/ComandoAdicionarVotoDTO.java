package com.jhemeson.desafiojava.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComandoAdicionarVotoDTO {
    private Long sessaoVotacao;
    private String associado;
    private boolean ehVotoAprovativo;
}
