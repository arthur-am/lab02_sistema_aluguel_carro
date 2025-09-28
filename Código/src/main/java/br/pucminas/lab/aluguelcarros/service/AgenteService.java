package br.pucminas.lab.aluguelcarros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.pucminas.lab.aluguelcarros.model.Agente;
import br.pucminas.lab.aluguelcarros.repository.AgenteRepository;

@Service
public class AgenteService {
    @Autowired
    private AgenteRepository agenteRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    public List<Agente> buscarPorTipo(Agente.TipoAgente tipo) {
        return agenteRepository.findByTipo(tipo);
    }

     public List<Agente> buscarTodos() {
        return agenteRepository.findAll();
    }
    
    public Agente salvar(Agente agente) {
        if (agente.getId() == null) {
            if (agenteRepository.existsByCnpj(agente.getCnpj())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CNPJ já cadastrado.");
            }
            if (agenteRepository.existsByEmail(agente.getEmail())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado.");
            }
        }

        agente.setSenha(passwordEncoder.encode(agente.getSenha()));
        return agenteRepository.save(agente);
    }
}
