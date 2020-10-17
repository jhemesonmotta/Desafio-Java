package com.jhemeson.desafiojava.service;

import com.jhemeson.desafiojava.dto.AssociadoDTO;
import com.jhemeson.desafiojava.dto.MessageResponseDTO;
import com.jhemeson.desafiojava.entity.Associado;
import com.jhemeson.desafiojava.mapper.AssociadoMapper;
import com.jhemeson.desafiojava.repository.AssociadoRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {
    private AssociadoRepository associadoRepository;
    private static final AssociadoMapper associadoMapper = AssociadoMapper.INSTANCE;

    @Autowired
    public AssociadoService(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    public MessageResponseDTO create(AssociadoDTO associadoDTO) {
        Associado associadoToCreate = associadoMapper.toModel(associadoDTO);
        Associado associadoCreated = associadoRepository.save(associadoToCreate);

        return MessageResponseDTO.builder().
                message("Associado " + associadoCreated.getNome() + " foi adicionado à base de dados com o CPF:" + associadoCreated.getCpf())
                .build();
    }

    public AssociadoDTO findById(String cpf) throws NotFoundException {
        Associado associado = associadoRepository.findByCpf(cpf);
        return associadoMapper.toDTO(associado);
    }
}
