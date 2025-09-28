package br.pucminas.lab.aluguelcarros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pucminas.lab.aluguelcarros.model.Agente;

@Repository
public interface AgenteRepository extends JpaRepository<Agente, Long> {
    Optional<Agente> findByEmail(String email);
    List<Agente> findByTipo(Agente.TipoAgente tipo);
    boolean existsByCnpj(String cnpj);
    boolean existsByEmail(String email);
}
