package br.pucminas.lab.aluguelcarros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.pucminas.lab.aluguelcarros.model.Automovel;
import br.pucminas.lab.aluguelcarros.service.AutomovelService;

@Controller
@RequestMapping("/automoveis")
public class AutomovelController {

    @Autowired
    private AutomovelService automovelService;

    @GetMapping
    public String listarAutomoveis(Model model) {
        model.addAttribute("automoveis", automovelService.buscarTodos());
        return "lista-automoveis";
    }

    @GetMapping("/novo")
    public String novoAutomovelForm(Model model) {
        model.addAttribute("automovel", new Automovel());
        return "form-automovel";
    }

    @PostMapping("/salvar")
    public String salvarAutomovel(@ModelAttribute Automovel automovel) {
        automovelService.salvar(automovel);
        return "redirect:/automoveis";
    }

    @GetMapping("/editar/{id}")
    public String editarAutomovelForm(@PathVariable Long id, Model model) {
        Automovel automovel = automovelService.buscarPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("Automóvel inválido:" + id));
        model.addAttribute("automovel", automovel);
        return "form-automovel";
    }

    @PostMapping("/deletar/{id}")
    public String deletarAutomovel(@PathVariable Long id) {
        automovelService.deletarPorId(id);
        return "redirect:/automoveis";
    }
}
