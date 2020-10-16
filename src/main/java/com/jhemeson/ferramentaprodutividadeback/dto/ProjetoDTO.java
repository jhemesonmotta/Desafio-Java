package com.jhemeson.ferramentaprodutividadeback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoDTO {
    private Long id;
    private String nome;
    private String linguagemProgramacao;
    private String homePage;
    private Double diversidade;
    private Double frequenciaCommits;
    private Double tamanhoComunidade;
    private Double qtdContribuintes;
    private Double qtdCommits;
    private Double correlacaoTamanho;
    private PageSpeedQualidadeDTO qualidade;
}
