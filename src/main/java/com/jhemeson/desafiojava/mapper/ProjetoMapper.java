package com.jhemeson.desafiojava.mapper;

import com.jhemeson.desafiojava.dto.ProjetoDTO;
import com.jhemeson.desafiojava.entity.Projeto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjetoMapper {
    ProjetoMapper INSTANCE = Mappers.getMapper(ProjetoMapper.class);

    Projeto toModel(ProjetoDTO projetoDTO);

    ProjetoDTO toDTO(Projeto projeto);
}
