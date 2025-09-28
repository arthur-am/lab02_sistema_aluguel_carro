package br.pucminas.lab.aluguelcarros.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.pucminas.lab.aluguelcarros.model.Automovel;
import br.pucminas.lab.aluguelcarros.model.PedidoAluguel;
import br.pucminas.lab.aluguelcarros.model.StatusPedido;
import br.pucminas.lab.aluguelcarros.repository.PedidoAluguelRepository;
import jakarta.transaction.Transactional;

@Service
public class PedidoAluguelService {

    @Autowired
    private PedidoAluguelRepository pedidoAluguelRepository;

    public List<PedidoAluguel> buscarTodos() {
        return pedidoAluguelRepository.findAll();
    }

    public Optional<PedidoAluguel> buscarPorId(Long id) {
        return pedidoAluguelRepository.findById(id);
    }

    public List<PedidoAluguel> buscarPorCliente(Long clienteId) {
        return pedidoAluguelRepository.findByClienteId(clienteId);
    }

    public PedidoAluguel criarPedido(PedidoAluguel pedido) {
        pedido.setDataPedido(LocalDate.now());
        pedido.setStatus(StatusPedido.EM_ANALISE);
        return pedidoAluguelRepository.save(pedido);
    }
    
    public void cancelarPedido(Long id) {
        PedidoAluguel pedido = pedidoAluguelRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        pedido.setStatus(StatusPedido.CANCELADO);
        pedidoAluguelRepository.save(pedido);
    }
    
    public void atualizarStatus(Long id, StatusPedido status) {
        PedidoAluguel pedido = pedidoAluguelRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        pedido.setStatus(status);
        pedidoAluguelRepository.save(pedido);
    }

    public Optional<PedidoAluguel> buscarPorIdECliente(Long id, String emailCliente) {
        PedidoAluguel pedido = pedidoAluguelRepository.findById(id).orElse(null);
        if (pedido != null && pedido.getCliente().getEmail().equals(emailCliente)) {
            return Optional.of(pedido);
        }
        return Optional.empty();
    }

    @Transactional
    public void atualizarPedido(Long id, Automovel novoAutomovel, String emailCliente) {
        PedidoAluguel pedido = buscarPorIdECliente(id, emailCliente)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado."));
        
        if (pedido.getStatus() == StatusPedido.EM_ANALISE) {
            pedido.setAutomovel(novoAutomovel);
            pedidoAluguelRepository.save(pedido);
        } else {
            throw new IllegalStateException("Este pedido não pode mais ser modificado.");
        }
    }

     @Transactional
    public void agenteAtualizarPedido(Long id, Automovel novoAutomovel) {
        PedidoAluguel pedido = pedidoAluguelRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        
        pedido.setAutomovel(novoAutomovel);
        pedidoAluguelRepository.save(pedido);
    }
}
