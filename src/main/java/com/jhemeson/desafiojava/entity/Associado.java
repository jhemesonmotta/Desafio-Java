package com.jhemeson.desafiojava.entity;

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
public class Associado {
    @Id
    @Column(nullable = false, unique = true)
    private String cpf;

    @Column
    private String nome;
}
