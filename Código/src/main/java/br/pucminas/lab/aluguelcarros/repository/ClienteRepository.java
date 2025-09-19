package br.pucminas.lab.aluguelcarros.repository;

import br.pucminas.lab.aluguelcarros.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
