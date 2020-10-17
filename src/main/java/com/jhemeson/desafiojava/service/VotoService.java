package com.jhemeson.desafiojava.service;

import com.jhemeson.desafiojava.dto.MessageResponseDTO;
import com.jhemeson.desafiojava.dto.VotoDTO;
import com.jhemeson.desafiojava.entity.SessaoVotacao;
import com.jhemeson.desafiojava.entity.Voto;
import com.jhemeson.desafiojava.mapper.VotoMapper;
import com.jhemeson.desafiojava.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoService {
    private VotoRepository votoRepository;
    private static final VotoMapper votoMapper = VotoMapper.INSTANCE;

    @Autowired
    public VotoService(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    public MessageResponseDTO create(VotoDTO votoDTO) {
        Voto votoToCreate = votoMapper.toModel(votoDTO);
        Voto votoCreated = votoRepository.save(votoToCreate);

        return MessageResponseDTO.builder().
                message("Voto adicionado a Sessão" + votoCreated.getSessaoVotacao().getId() + " com o ID:" + votoCreated.getId())
                .build();
    }

    public List<Voto> findAllBySessaoVotacao_Id(Long idSessao) {
        return votoRepository.findAllBySessaoVotacao_Id(idSessao);
    }

    public VotoDTO findBySessaoVotacao_IdAndAssociado_Cpf(Long sessaoVotacaoId, String cpf) {
        Voto voto = votoRepository.findBySessaoVotacao_IdAndAssociado_Cpf(sessaoVotacaoId, cpf);
        return votoMapper.toDTO(voto);
    }
}
