package br.pucminas.lab.aluguelcarros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucminas.lab.aluguelcarros.model.Automovel;
import br.pucminas.lab.aluguelcarros.repository.AutomovelRepository;

@Service
public class AutomovelService {

    @Autowired
    private AutomovelRepository automovelRepository;

    public List<Automovel> buscarTodos() {
        return automovelRepository.findAll();
    }
    
    public Automovel salvar(Automovel automovel) {
        return automovelRepository.save(automovel);
    }
}
