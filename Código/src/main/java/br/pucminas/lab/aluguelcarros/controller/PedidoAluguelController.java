package br.pucminas.lab.aluguelcarros.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.pucminas.lab.aluguelcarros.model.Cliente;
import br.pucminas.lab.aluguelcarros.model.PedidoAluguel;
import br.pucminas.lab.aluguelcarros.repository.ClienteRepository;
import br.pucminas.lab.aluguelcarros.service.AutomovelService;
import br.pucminas.lab.aluguelcarros.service.PedidoAluguelService;

@Controller
@RequestMapping("/pedidos")
public class PedidoAluguelController {

    @Autowired
    private PedidoAluguelService pedidoAluguelService;

    @Autowired
    private AutomovelService automovelService;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/meus-pedidos")
    public String meusPedidos(Model model) {
        model.addAttribute("pedidos", pedidoAluguelService.buscarTodos());
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
        String emailDoUsuarioLogado = principal.getName();
        
        Cliente clienteLogado = clienteRepository.findByEmail(emailDoUsuarioLogado)
                .orElseThrow(() -> new IllegalStateException("Cliente logado não encontrado no banco de dados."));

        pedido.setCliente(clienteLogado);
        pedidoAluguelService.criarPedido(pedido);

        return "redirect:/pedidos/meus-pedidos";    
    }
}
