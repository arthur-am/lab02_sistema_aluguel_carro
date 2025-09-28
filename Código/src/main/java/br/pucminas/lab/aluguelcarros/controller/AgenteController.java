package br.pucminas.lab.aluguelcarros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.pucminas.lab.aluguelcarros.model.StatusPedido;
import br.pucminas.lab.aluguelcarros.service.ClienteService;
import br.pucminas.lab.aluguelcarros.service.PedidoAluguelService;

@Controller
@RequestMapping("/agente")
public class AgenteController {

    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private PedidoAluguelService pedidoAluguelService;

    @GetMapping("/dashboard")
    public String dashboardAgente(Model model) {
        model.addAttribute("pedidos", pedidoAluguelService.buscarTodos());
        return "dashboard-agente";
    }

    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.buscarTodos());
        return "lista-clientes";
    }
    
    @GetMapping("/clientes/deletar/{id}")
    public String deletarCliente(@PathVariable Long id) {
        clienteService.deletarPorId(id);
        return "redirect:/agente/clientes";
    }

    @PostMapping("/pedidos/avaliar/{id}")
    public String avaliarPedido(@PathVariable Long id, @RequestParam("status") String status) {
        StatusPedido novoStatus = StatusPedido.valueOf(status.toUpperCase());
        pedidoAluguelService.atualizarStatus(id, novoStatus);
        return "redirect:/agente/dashboard";
    }
}