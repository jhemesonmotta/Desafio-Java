package com.jhemeson.desafiojava.external.integrations;

import com.jhemeson.desafiojava.exceptions.CPFInvalidoException;
import org.hibernate.secure.spi.IntegrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class UsuarioExternoService {
    private final RestTemplate restTemplate;

    @Autowired
    public UsuarioExternoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean podeVotar(String cpf) throws CPFInvalidoException {
        try {
            String url = "" + cpf;
            ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            return response.getBody().equals("ABLE_TO_VOTE");
        } catch (HttpStatusCodeException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new CPFInvalidoException(cpf);
            throw new IntegrationException("Um erro ocorreu ao tentar acessar o serviço.");
        }
    }
}
