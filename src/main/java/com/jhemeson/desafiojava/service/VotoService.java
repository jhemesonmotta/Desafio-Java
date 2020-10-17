package com.jhemeson.desafiojava.service;

import com.jhemeson.desafiojava.mapper.VotoMapper;
import com.jhemeson.desafiojava.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotoService {
    private VotoRepository votoRepository;
    private static final VotoMapper votoMapper = VotoMapper.INSTANCE;

    @Autowired
    public VotoService(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    // CREATE

    // Find by Sessão

    // find by Sessão And Associado
}
