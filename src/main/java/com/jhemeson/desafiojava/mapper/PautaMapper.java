package com.jhemeson.desafiojava.mapper;

import com.jhemeson.desafiojava.dto.PautaDTO;
import com.jhemeson.desafiojava.entity.Pauta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PautaMapper {
    PautaMapper INSTANCE = Mappers.getMapper(PautaMapper.class);

    Pauta toModel(PautaDTO pautaDTO);

    PautaDTO toDTO(Pauta pauta);
}
