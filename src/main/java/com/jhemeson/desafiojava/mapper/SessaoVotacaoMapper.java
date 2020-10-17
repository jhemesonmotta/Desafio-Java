package com.jhemeson.desafiojava.mapper;

import com.jhemeson.desafiojava.dto.SessaoVotacaoDTO;
import com.jhemeson.desafiojava.entity.SessaoVotacao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SessaoVotacaoMapper {
    SessaoVotacaoMapper INSTANCE = Mappers.getMapper(SessaoVotacaoMapper.class);

    SessaoVotacao toModel(SessaoVotacaoDTO sessaoVotacaoDTO);

    SessaoVotacaoDTO toDTO(SessaoVotacao sessaoVotacao);
}
