package br.pucminas.lab.aluguelcarros.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import br.pucminas.lab.aluguelcarros.model.Cliente;
import br.pucminas.lab.aluguelcarros.model.PedidoAluguel;
import br.pucminas.lab.aluguelcarros.model.StatusPedido;
import br.pucminas.lab.aluguelcarros.service.AutomovelService;
import br.pucminas.lab.aluguelcarros.service.ClienteService;
import br.pucminas.lab.aluguelcarros.service.PedidoAluguelService;

@Controller
@RequestMapping("/pedidos")
public class PedidoAluguelController {

    @Autowired
    private PedidoAluguelService pedidoAluguelService;
    @Autowired
    private AutomovelService automovelService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/meus-pedidos")
    public String meusPedidos(Model model, Principal principal) {
        Cliente cliente = clienteService.buscarPorEmail(principal.getName()).orElse(null);
        model.addAttribute("pedidos", pedidoAluguelService.buscarPorCliente(cliente.getId()));
        return "dashboard-cliente";
    }

    @GetMapping("/novo")
    public String novoPedidoForm(Model model) {
        model.addAttribute("pedido", new PedidoAluguel());
        model.addAttribute("automoveis", automovelService.buscarTodos());
        return "form-pedido";
    }

    @PostMapping("/salvar")
    public String salvarPedido(@ModelAttribute("pedido") PedidoAluguel pedido, Principal principal) {
        Cliente clienteLogado = clienteService.buscarPorEmail(principal.getName())
                .orElseThrow(() -> new IllegalStateException("Cliente logado não encontrado."));
        pedido.setCliente(clienteLogado);
        pedidoAluguelService.criarPedido(pedido);
        return "redirect:/pedidos/meus-pedidos";    
    }

    @PostMapping("/cancelar/{id}")
    public String cancelarPedido(@PathVariable Long id, Principal principal) {
        Cliente clienteLogado = clienteService.buscarPorEmail(principal.getName()).orElseThrow();
        pedidoAluguelService.cancelarPedido(id);
        return "redirect:/pedidos/meus-pedidos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarPedido(@PathVariable Long id, Model model, Principal principal) {
        PedidoAluguel pedido = pedidoAluguelService.buscarPorIdECliente(id, principal.getName())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado ou não pertence a você."));
        
        if (pedido.getStatus() != StatusPedido.EM_ANALISE) {
            return "redirect:/pedidos/meus-pedidos?error=not_editable";
        }
        
        model.addAttribute("pedido", pedido);
        model.addAttribute("automoveis", automovelService.buscarTodos());
        return "form-pedido-editar";
    }

    @PostMapping("/editar/salvar")
    public String atualizarPedido(@ModelAttribute("pedido") PedidoAluguel pedido, Principal principal) {
        pedidoAluguelService.atualizarPedido(pedido.getId(), pedido.getAutomovel(), principal.getName());
        return "redirect:/pedidos/meus-pedidos";
    }
}