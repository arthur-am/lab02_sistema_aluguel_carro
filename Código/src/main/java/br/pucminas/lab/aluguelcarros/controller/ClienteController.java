package br.pucminas.lab.aluguelcarros.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
import br.pucminas.lab.aluguelcarros.model.Rendimento;
import br.pucminas.lab.aluguelcarros.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/perfil")
    public String mostrarFormularioPerfil(Model model, Principal principal) {
        String email = principal.getName();
        Cliente cliente = clienteService.buscarPorEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        
        if (cliente.getRendimentos() == null || cliente.getRendimentos().isEmpty()) {
            cliente.setRendimentos(new ArrayList<>());
            cliente.getRendimentos().add(new Rendimento());
        }

        model.addAttribute("cliente", cliente);
        return "form-perfil";
    }

    @PostMapping("/perfil/salvar")
    public String atualizarPerfil(@ModelAttribute("cliente") Cliente cliente, Principal principal) {
        String emailAutenticado = principal.getName();
        clienteService.atualizar(emailAutenticado, cliente);
        return "redirect:/clientes/perfil?success";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        Cliente cliente = new Cliente();
        List<Rendimento> rendimentos = new ArrayList<>();
        rendimentos.add(new Rendimento());
        cliente.setRendimentos(rendimentos);

        model.addAttribute("cliente", cliente);
        return "form-cliente";
    }

    @PostMapping("/salvar")
    public String salvarCliente(@ModelAttribute("cliente") Cliente cliente, Model model) {
        try {
            if (cliente.getRendimentos() != null && !cliente.getRendimentos().isEmpty()) {
                cliente.getRendimentos().forEach(rendimento -> rendimento.setCliente(cliente));
            }
            
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