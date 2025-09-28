package br.pucminas.lab.aluguelcarros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import br.pucminas.lab.aluguelcarros.model.Agente;
import br.pucminas.lab.aluguelcarros.model.Cliente;
import br.pucminas.lab.aluguelcarros.model.PedidoAluguel;
import br.pucminas.lab.aluguelcarros.model.StatusPedido;
import br.pucminas.lab.aluguelcarros.service.AgenteService;
import br.pucminas.lab.aluguelcarros.service.AutomovelService;
import br.pucminas.lab.aluguelcarros.service.ClienteService;
import br.pucminas.lab.aluguelcarros.service.PedidoAluguelService;

@Controller
@RequestMapping("/agente")
public class AgenteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PedidoAluguelService pedidoAluguelService;

    @Autowired
    private AutomovelService automovelService;

    @Autowired 
    private AgenteService agenteService;

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

    @PostMapping("/pedidos/avaliar/{id}")
    public String avaliarPedido(@PathVariable Long id, @RequestParam("status") String status) {
        StatusPedido novoStatus = StatusPedido.valueOf(status.toUpperCase());
        pedidoAluguelService.atualizarStatus(id, novoStatus);
        return "redirect:/agente/dashboard";
    }

    @GetMapping("/pedidos/editar/{id}")
    public String mostrarFormularioEditarPedido(@PathVariable Long id, Model model) {
        PedidoAluguel pedido = pedidoAluguelService.buscarPorId(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
        
        model.addAttribute("pedido", pedido);
        model.addAttribute("automoveis", automovelService.buscarTodos());
        return "form-pedido-agente";
    }

    @PostMapping("/pedidos/editar/salvar")
    public String atualizarPedido(@ModelAttribute("pedido") PedidoAluguel pedido) {
        pedidoAluguelService.agenteAtualizarPedido(pedido.getId(), pedido.getAutomovel());
        return "redirect:/agente/dashboard";
    }

    @GetMapping("/clientes/{id}")
    public String detalhesCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
        model.addAttribute("cliente", cliente);
        return "detalhes-cliente";
    }

     @GetMapping("/gerenciar")
    public String gerenciarAgentes(Model model) {
        model.addAttribute("agentes", agenteService.buscarTodos());
        return "lista-agentes";
    }

    @GetMapping("/novo")
    public String novoAgenteForm(Model model) {
        model.addAttribute("agente", new Agente());
        return "form-agente";
    }

    @PostMapping("/salvar")
    public String salvarAgente(@ModelAttribute Agente agente, Model model) {
        try {
            agenteService.salvar(agente);
            return "redirect:/agente/gerenciar";
        } catch (ResponseStatusException e) {
            model.addAttribute("errorMessage", e.getReason());
            model.addAttribute("agente", agente);
            return "form-agente";
        }
    }
}
