package br.pucminas.lab.aluguelcarros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pucminas.lab.aluguelcarros.model.PedidoAluguel;

@Repository
public interface PedidoAluguelRepository extends JpaRepository<PedidoAluguel, Long> {
    List<PedidoAluguel> findByClienteId(Long clienteId);
}
