package com.jhemeson.desafiojava.service;

import com.jhemeson.desafiojava.dto.MessageResponseDTO;
import com.jhemeson.desafiojava.dto.SessaoVotacaoDTO;
import com.jhemeson.desafiojava.entity.SessaoVotacao;
import com.jhemeson.desafiojava.mapper.SessaoVotacaoMapper;
import com.jhemeson.desafiojava.repository.SessaoVotacaoRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessaoVotacaoService {
    private SessaoVotacaoRepository sessaoVotacaoRepository;
    private static final SessaoVotacaoMapper sessaoVotacaoMapper = SessaoVotacaoMapper.INSTANCE;

    @Autowired
    public SessaoVotacaoService(SessaoVotacaoRepository sessaoVotacaoRepository) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
    }

    public MessageResponseDTO create(SessaoVotacaoDTO pautaDTO) {
        SessaoVotacao sessaoVotacaoToCreate = sessaoVotacaoMapper.toModel(pautaDTO);
        SessaoVotacao sessaoVotacaoCreated = sessaoVotacaoRepository.save(sessaoVotacaoToCreate);

        return MessageResponseDTO.builder().
                message("Sessão de Votação criada na pauta" + sessaoVotacaoCreated.getPauta().getNome() + " com o ID:" + sessaoVotacaoCreated.getId())
                .build();
    }

    public SessaoVotacaoDTO findById(Long id) throws NotFoundException {
        SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findById(id).orElseThrow(() -> new NotFoundException("Sessão de Votação não encontrada."));
        return sessaoVotacaoMapper.toDTO(sessaoVotacao);
    }

}
