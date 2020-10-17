package com.jhemeson.desafiojava.service;

import com.jhemeson.desafiojava.dto.ComandoAdicionarVotoDTO;
import com.jhemeson.desafiojava.dto.MessageResponseDTO;
import com.jhemeson.desafiojava.dto.VotoDTO;
import com.jhemeson.desafiojava.entity.SessaoVotacao;
import com.jhemeson.desafiojava.entity.Voto;
import com.jhemeson.desafiojava.mapper.VotoMapper;
import com.jhemeson.desafiojava.repository.VotoRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoService {
    private VotoRepository votoRepository;
    private SessaoVotacaoService sessaoVotacaoService;
    private static final VotoMapper votoMapper = VotoMapper.INSTANCE;

    @Autowired
    public VotoService(VotoRepository votoRepository, SessaoVotacaoService sessaoVotacaoService) {
        this.votoRepository = votoRepository;
        this.sessaoVotacaoService = sessaoVotacaoService;
    }

    public MessageResponseDTO create(ComandoAdicionarVotoDTO comandoAdicionarVotoDTO) throws NotFoundException {
        VotoDTO votoDTO = new VotoDTO().builder()
                .ehVotoAprovativo(comandoAdicionarVotoDTO.isEhVotoAprovativo())
                .associado(comandoAdicionarVotoDTO.getAssociado())
                .sessaoVotacao(sessaoVotacaoService.findById(comandoAdicionarVotoDTO.getSessaoVotacao()))
                .build();

        // TODO: Validar se sessão está ativa
        // TODO: Validar se user já votou

        Voto votoToCreate = votoMapper.toModel(votoDTO);
        Voto votoCreated = votoRepository.save(votoToCreate);

        return MessageResponseDTO.builder().
                message("Voto adicionado a Sessão " + votoCreated.getSessaoVotacao().getId() + " com o ID:" + votoCreated.getId())
                .build();
    }

    public List<Voto> findAllBySessaoVotacao_Id(Long idSessao) {
        return votoRepository.findAllBySessaoVotacao_Id(idSessao);
    }

    public VotoDTO findBySessaoVotacao_IdAndAssociado(Long sessaoVotacaoId, String cpf) {
        Voto voto = votoRepository.findBySessaoVotacao_IdAndAssociado(sessaoVotacaoId, cpf);
        return votoMapper.toDTO(voto);
    }
}
