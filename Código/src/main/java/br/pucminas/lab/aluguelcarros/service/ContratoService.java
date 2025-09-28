package br.pucminas.lab.aluguelcarros.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucminas.lab.aluguelcarros.model.Agente;
import br.pucminas.lab.aluguelcarros.model.Contrato;
import br.pucminas.lab.aluguelcarros.model.PedidoAluguel;
import br.pucminas.lab.aluguelcarros.model.StatusPedido;
import br.pucminas.lab.aluguelcarros.repository.AgenteRepository;
import br.pucminas.lab.aluguelcarros.repository.ContratoRepository;
import jakarta.transaction.Transactional;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private PedidoAluguelService pedidoAluguelService;

    @Autowired
    private AgenteRepository agenteRepository;

    @Transactional
    public void gerarContrato(Contrato contratoVindoDoForm, String emailAgente) {
        Long pedidoId = contratoVindoDoForm.getPedidoAluguel().getId();
        PedidoAluguel pedido = pedidoAluguelService.buscarPorId(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido associado ao contrato não encontrado."));

        Agente agenteLogado = agenteRepository.findByEmail(emailAgente)
                .orElseThrow(() -> new IllegalArgumentException("Agente não encontrado."));

        contratoVindoDoForm.setPedidoAluguel(pedido);
        contratoVindoDoForm.setAgente(agenteLogado);
        contratoVindoDoForm.setDataContrato(LocalDate.now());
        
        if (contratoVindoDoForm.getBancoCredor() != null && contratoVindoDoForm.getBancoCredor().getId() != null) {
            Agente banco = agenteRepository.findById(contratoVindoDoForm.getBancoCredor().getId()).orElse(null);
            contratoVindoDoForm.setBancoCredor(banco);
        } else {
            contratoVindoDoForm.setBancoCredor(null);
        }

        contratoRepository.save(contratoVindoDoForm);
        pedidoAluguelService.atualizarStatus(pedidoId, StatusPedido.CONTRATO_GERADO);
    }
}
