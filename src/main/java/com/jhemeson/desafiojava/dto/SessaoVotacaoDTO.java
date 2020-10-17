package com.jhemeson.desafiojava.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
public class SessaoVotacaoDTO {
    private Long id;
    private PautaDTO pauta;
    private Integer tempoDeAberturaEmSegundos;
    private Date dataHoraAbertura;

    public SessaoVotacaoDTO(PautaDTO pauta, Date dataHoraAbertura, Integer tempoDeAberturaEmSegundos) {
        this.pauta = pauta;
        this.dataHoraAbertura = dataHoraAbertura;

        if (tempoDeAberturaEmSegundos == null || tempoDeAberturaEmSegundos == 0) {
            this.tempoDeAberturaEmSegundos = 60;
        } else {
            this.tempoDeAberturaEmSegundos = tempoDeAberturaEmSegundos;
        }
    }
}
