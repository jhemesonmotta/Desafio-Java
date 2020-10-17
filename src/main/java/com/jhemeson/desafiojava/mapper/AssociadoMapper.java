package com.jhemeson.desafiojava.mapper;

import com.jhemeson.desafiojava.dto.AssociadoDTO;
import com.jhemeson.desafiojava.entity.Associado;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AssociadoMapper {
    AssociadoMapper INSTANCE = Mappers.getMapper(AssociadoMapper.class);

    Associado toModel(AssociadoDTO associadoDTO);

    AssociadoDTO toDTO(Associado associado);
}
