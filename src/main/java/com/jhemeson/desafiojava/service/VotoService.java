package com.jhemeson.desafiojava.service;

import com.jhemeson.desafiojava.dto.ComandoAdicionarVotoDTO;
import com.jhemeson.desafiojava.dto.MessageResponseDTO;
import com.jhemeson.desafiojava.dto.VotoDTO;
import com.jhemeson.desafiojava.entity.Voto;
import com.jhemeson.desafiojava.exceptions.SessaoExpiradaException;
import com.jhemeson.desafiojava.exceptions.VotoExistenteException;
import com.jhemeson.desafiojava.mapper.VotoMapper;
import com.jhemeson.desafiojava.repository.VotoRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    public MessageResponseDTO create(ComandoAdicionarVotoDTO comandoAdicionarVotoDTO) throws NotFoundException, SessaoExpiradaException, VotoExistenteException {
        VotoDTO votoDTO = new VotoDTO().builder()
                .ehVotoAprovativo(comandoAdicionarVotoDTO.isEhVotoAprovativo())
                .associado(comandoAdicionarVotoDTO.getAssociado())
                .sessaoVotacao(sessaoVotacaoService.findById(comandoAdicionarVotoDTO.getSessaoVotacao()))
                .build();

        validaSeJaVotou(votoDTO);
        validaStatusSessao(votoDTO);

        Voto votoToCreate = votoMapper.toModel(votoDTO);
        Voto votoCreated = votoRepository.save(votoToCreate);

        return MessageResponseDTO.builder().
                message("Voto adicionado a Sessão " + votoCreated.getSessaoVotacao().getId() + " com o ID:" + votoCreated.getId())
                .build();
    }

    private void validaStatusSessao(VotoDTO votoDTO) throws SessaoExpiradaException {
        Date deadlineParaVotacao = votoDTO.getSessaoVotacao().getDataHoraAbertura();
        deadlineParaVotacao.setTime(deadlineParaVotacao.getTime() + (votoDTO.getSessaoVotacao().getTempoDeAberturaEmSegundos() * 1000));

        if (deadlineParaVotacao.before(new Date())) {
            throw new SessaoExpiradaException(votoDTO.getSessaoVotacao().getId());
        }
    }

    private void validaSeJaVotou(VotoDTO votoDTO) throws VotoExistenteException {
        VotoDTO votoComputado = findBySessaoVotacaoIdAndAssociado(votoDTO.getAssociado(), votoDTO.getSessaoVotacao().getId());

        if (votoComputado != null && votoComputado.getId() != null) {
            throw new VotoExistenteException(votoDTO.getAssociado(), votoDTO.getSessaoVotacao().getId());
        }
    }

    public VotoDTO findBySessaoVotacaoIdAndAssociado(String cpf, Long sessaoVotacaoId) {
        Voto voto = votoRepository.findBySessaoVotacao_IdAndAssociado(sessaoVotacaoId, cpf);
        return votoMapper.toDTO(voto);
    }
}
