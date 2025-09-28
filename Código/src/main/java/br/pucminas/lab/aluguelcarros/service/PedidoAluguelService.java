package br.pucminas.lab.aluguelcarros.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucminas.lab.aluguelcarros.model.PedidoAluguel;
import br.pucminas.lab.aluguelcarros.model.StatusPedido;
import br.pucminas.lab.aluguelcarros.repository.PedidoAluguelRepository;

@Service
public class PedidoAluguelService {

    @Autowired
    private PedidoAluguelRepository pedidoAluguelRepository;

    public List<PedidoAluguel> buscarTodos() {
        return pedidoAluguelRepository.findAll();
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
}