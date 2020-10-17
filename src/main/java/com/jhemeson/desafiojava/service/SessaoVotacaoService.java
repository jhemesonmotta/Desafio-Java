package com.jhemeson.desafiojava.service;

import com.jhemeson.desafiojava.dto.ComandoAbrirSessaoVotacaoDTO;
import com.jhemeson.desafiojava.dto.MessageResponseDTO;
import com.jhemeson.desafiojava.dto.ResultadoSessaoVotacaoDTO;
import com.jhemeson.desafiojava.dto.SessaoVotacaoDTO;
import com.jhemeson.desafiojava.entity.SessaoVotacao;
import com.jhemeson.desafiojava.entity.Voto;
import com.jhemeson.desafiojava.mapper.SessaoVotacaoMapper;
import com.jhemeson.desafiojava.repository.SessaoVotacaoRepository;
import com.jhemeson.desafiojava.repository.VotoRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SessaoVotacaoService {
    private VotoRepository votoRepository;
    private SessaoVotacaoRepository sessaoVotacaoRepository;
    private static final SessaoVotacaoMapper sessaoVotacaoMapper = SessaoVotacaoMapper.INSTANCE;
    private PautaService pautaService;

    @Autowired
    public SessaoVotacaoService(SessaoVotacaoRepository sessaoVotacaoRepository, VotoRepository votoRepository, PautaService pautaService) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
        this.votoRepository = votoRepository;
        this.pautaService = pautaService;
    }

    public MessageResponseDTO create(ComandoAbrirSessaoVotacaoDTO comandoAbrirSessaoVotacaoDTO) throws NotFoundException {

        SessaoVotacaoDTO sessaoVotacaoDTO = new SessaoVotacaoDTO().builder()
                .pauta(pautaService.findById(comandoAbrirSessaoVotacaoDTO.getPauta()))
                .tempoDeAberturaEmSegundos(decideTempoDeAberturaEmSegundos(comandoAbrirSessaoVotacaoDTO.getTempoDeAberturaEmSegundos()))
                .dataHoraAbertura(new Date())
                .build();

        SessaoVotacao sessaoVotacaoToCreate = sessaoVotacaoMapper.toModel(sessaoVotacaoDTO);
        SessaoVotacao sessaoVotacaoCreated = sessaoVotacaoRepository.save(sessaoVotacaoToCreate);

        return MessageResponseDTO.builder().
                message("Sessão de Votação criada na pauta " + sessaoVotacaoCreated.getPauta().getNome() + " com o ID:" + sessaoVotacaoCreated.getId())
                .build();
    }

    public SessaoVotacaoDTO findById(Long id) throws NotFoundException {
        SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findById(id).orElseThrow(() -> new NotFoundException("Sessão de Votação não encontrada."));
        return sessaoVotacaoMapper.toDTO(sessaoVotacao);
    }

    public ResultadoSessaoVotacaoDTO buscarResultadoSessaoVotacao(Long id) throws NotFoundException {
        SessaoVotacaoDTO sessaoVotacaoDTO = findById(id);
        List<Voto> votos = findAllBySessaoVotacaoId(id);

        return new ResultadoSessaoVotacaoDTO().builder()
                .id(id)
                .pauta(sessaoVotacaoDTO.getPauta())
                .votosContra(votos.stream().filter(voto -> !voto.isEhVotoAprovativo()).count())
                .votosFavoraveis(votos.stream().filter(voto -> voto.isEhVotoAprovativo()).count())
                .build();
    }

    private Integer decideTempoDeAberturaEmSegundos(Integer tempoDeAberturaEmSegundos) {
        return (tempoDeAberturaEmSegundos == null || tempoDeAberturaEmSegundos <= 0) ? 60 : tempoDeAberturaEmSegundos;
    }

    private List<Voto> findAllBySessaoVotacaoId(Long idSessao) {
        return votoRepository.findAllBySessaoVotacao_Id(idSessao);
    }
}
