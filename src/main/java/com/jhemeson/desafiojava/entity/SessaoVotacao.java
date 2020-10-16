package com.jhemeson.desafiojava.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
public class SessaoVotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @Column
    private Integer tempoDeAberturaEmSegundos;

    public SessaoVotacao(Pauta pauta, Integer tempoDeAberturaEmSegundos) {
        this.pauta = pauta;

        if (tempoDeAberturaEmSegundos == null || tempoDeAberturaEmSegundos == 0) {
            this.tempoDeAberturaEmSegundos = 60;
        } else {
            this.tempoDeAberturaEmSegundos = tempoDeAberturaEmSegundos;
        }
    }
}
