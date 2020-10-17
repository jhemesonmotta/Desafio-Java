package com.jhemeson.desafiojava.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoDTO {
    private Long id;
    private String cpf;
    private String nome;
}
