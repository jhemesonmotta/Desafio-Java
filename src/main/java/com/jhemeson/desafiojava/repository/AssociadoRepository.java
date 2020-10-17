package com.jhemeson.desafiojava.repository;

import com.jhemeson.desafiojava.entity.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {
    Associado findByCpf(String cpf);
}
