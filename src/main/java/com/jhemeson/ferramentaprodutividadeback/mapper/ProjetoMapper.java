package com.jhemeson.ferramentaprodutividadeback.mapper;

import com.jhemeson.ferramentaprodutividadeback.dto.ProjetoDTO;
import com.jhemeson.ferramentaprodutividadeback.entity.Projeto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjetoMapper {

    ProjetoMapper INSTANCE = Mappers.getMapper(ProjetoMapper.class);

    Projeto toModel(ProjetoDTO bookDTO);

    ProjetoDTO toDTO(Projeto book);
}
