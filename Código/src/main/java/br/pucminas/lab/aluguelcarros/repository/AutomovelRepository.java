package br.pucminas.lab.aluguelcarros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pucminas.lab.aluguelcarros.model.Automovel;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, Long> {}
