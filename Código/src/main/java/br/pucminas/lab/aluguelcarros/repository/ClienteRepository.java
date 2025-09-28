package br.pucminas.lab.aluguelcarros.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pucminas.lab.aluguelcarros.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCpf(String cpf);
    Optional<Cliente> findByCpf(String cpf);
    Optional<Cliente> findByEmail(String email);
    boolean existsByEmail(String email);
}