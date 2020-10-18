package com.jhemeson.desafiojava.service;

import com.jhemeson.desafiojava.dto.ComandoAdicionarVotoDTO;
import com.jhemeson.desafiojava.dto.MessageResponseDTO;
import com.jhemeson.desafiojava.dto.VotoDTO;
import com.jhemeson.desafiojava.entity.Voto;
import com.jhemeson.desafiojava.exceptions.CPFInvalidoException;
import com.jhemeson.desafiojava.exceptions.GenericException;
import com.jhemeson.desafiojava.exceptions.SessaoExpiradaException;
import com.jhemeson.desafiojava.exceptions.VotoExistenteException;
import com.jhemeson.desafiojava.external.integrations.UsuarioExternoService;
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
    private UsuarioExternoService usuarioExternoService;
    private static final VotoMapper votoMapper = VotoMapper.INSTANCE;

    @Autowired
    public VotoService(VotoRepository votoRepository, SessaoVotacaoService sessaoVotacaoService, UsuarioExternoService usuarioExternoService) {
        this.votoRepository = votoRepository;
        this.sessaoVotacaoService = sessaoVotacaoService;
        this.usuarioExternoService = usuarioExternoService;
    }

    public MessageResponseDTO create(ComandoAdicionarVotoDTO comandoAdicionarVotoDTO) throws NotFoundException, SessaoExpiradaException, VotoExistenteException, CPFInvalidoException, GenericException {
        VotoDTO votoDTO = new VotoDTO().builder()
                .ehVotoAprovativo(comandoAdicionarVotoDTO.isEhVotoAprovativo())
                .associado(comandoAdicionarVotoDTO.getAssociado())
                .sessaoVotacao(sessaoVotacaoService.findById(comandoAdicionarVotoDTO.getSessaoVotacao()))
                .build();

        if (usuarioExternoService.podeVotar(votoDTO.getAssociado())) {
            validaSeJaVotou(votoDTO);
            validaStatusSessao(votoDTO);

            Voto votoToCreate = votoMapper.toModel(votoDTO);
            Voto votoCreated = votoRepository.save(votoToCreate);

            return MessageResponseDTO.builder()
                    .message("Voto adicionado")
                    .build();
        } else {
            throw new GenericException("Usuário não pode votar.");
        }
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
