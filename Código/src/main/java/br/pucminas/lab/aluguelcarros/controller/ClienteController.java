package br.pucminas.lab.aluguelcarros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import br.pucminas.lab.aluguelcarros.model.Cliente;
import br.pucminas.lab.aluguelcarros.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "form-cliente";
    }

@PostMapping("/salvar")
public String salvarCliente(@ModelAttribute("cliente") Cliente cliente, Model model) {
    try {
        cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
        clienteService.salvar(cliente);
        return "redirect:/login";
    } catch (ResponseStatusException e) {
        model.addAttribute("errorMessage", e.getReason()); 
        
        cliente.setSenha("");
        model.addAttribute("cliente", cliente);

        return "form-cliente";
        } 
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Cliente inválido:" + id));
        model.addAttribute("cliente", cliente);
        cliente.setSenha("");
        return "form-cliente";
    }
}
