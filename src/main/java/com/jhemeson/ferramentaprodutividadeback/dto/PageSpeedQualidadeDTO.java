package com.jhemeson.ferramentaprodutividadeback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageSpeedQualidadeDTO {
    private Long id;
    private Double total;
    private Double accesibilidade;
    private Double melhoresPraticas;
    private Double performance;
    private Double pwa;
    private Double seo;
}
