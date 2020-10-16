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
public class PageSpeedQualidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Double total;
    @Column
    private Double accesibilidade;
    @Column
    private Double melhoresPraticas;
    @Column
    private Double performance;
    @Column
    private Double pwa;
    @Column
    private Double seo;
}
