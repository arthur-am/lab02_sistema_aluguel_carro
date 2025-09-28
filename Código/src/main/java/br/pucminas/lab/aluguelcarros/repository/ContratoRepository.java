package br.pucminas.lab.aluguelcarros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pucminas.lab.aluguelcarros.model.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {}