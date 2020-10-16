package com.jhemeson.ferramentaprodutividadeback.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column
    private String linguagemProgramacao;

    @Column(name = "home_page")
    private String homePage;

    @Column
    private Double diversidade;

    @Column(name = "frequencia_commits")
    private Double frequenciaCommits;

    @Column(name = "tamanho_comunidade")
    private Double tamanhoComunidade;

    @Column(name = "qtd_contribuintes")
    private Double qtdContribuintes;

    @Column(name = "qtd_commits")
    private Double qtdCommits;

    @Column(name = "correlacao_tamanho")
    private Double correlacaoTamanho;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "qualidade_id")
    private PageSpeedQualidade qualidade;

}